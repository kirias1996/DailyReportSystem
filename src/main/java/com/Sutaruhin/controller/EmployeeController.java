package com.Sutaruhin.controller;



import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Sutaruhin.entity.Employee;
import com.Sutaruhin.service.EmployeeService;


@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//	複数の従業員情報の一覧表示
	@GetMapping("/list")
	public String getEmployeeList(Model model) {
		model.addAttribute("employeelist",service.getEmployeeList());
		return "employee/list";
	}

//	1人の従業員情報の詳細表示
	@GetMapping("/detail/{code}/")
	public String getEmployee(@PathVariable(name="code",required = false) String code,Model model) {
		model.addAttribute("title","社員番号："+code+"の従業員情報 詳細ページ");
		Optional<Employee> testEmployee =service.getEmployee(code,false);
		model.addAttribute("employee", testEmployee.get());
		return "employee/detail";
	}
//  新規登録
	@GetMapping("/regist")
	public String getRegister(@ModelAttribute Employee employee) {
		return "employee/regist";
	}
//	バリデーションチェックを実装する
	@PostMapping("/regist")
	public String postRegister(@Validated Employee employee,BindingResult res,Model model) {
//		パスワードが空文字かバリデーションチェックエラーがあった場合にエラーメッセージを返す。
		if (isEmptyPassword(employee.getPassword()) || res.hasErrors()) {
			model.addAttribute("password_error_message", "パスワードは入力必須です。");
			return getRegister(employee);
		}
//		未削除の社員番号重複チェック処理を実行(trueであればエラーメッセージを返す)
		if(checkDuplicateCode(employee)) {
			model.addAttribute("duplicate_code_message", "入力された社員番号は登録済みです。");
			return getRegister(employee);
		}
		else if(existDeletedEmployeeCode(employee)) {
			model.addAttribute("duplicate_code_message", "入力された社員番号を持つ従業員は削除されています。別の社員番号で再登録してください。");
			return getRegister(employee);
		}
		else {
			employee.setDeleteFlag(false);
			LocalDateTime dateTime= LocalDateTime.now();
			employee.setCreatedAt(dateTime);
			employee.setUpdatedAt(dateTime);
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
			service.saveEmployee(employee);
		}
		return "redirect:/employee/list";
	}

	//  編集
	@GetMapping("/update/{code}/")
	public String getEmployeeInfoUpdate(@PathVariable(name="code",required = false)String code,Model model) {
		model.addAttribute("title","社員番号："+code+"の従業員情報 編集ページ");
		model.addAttribute("employee",service.getEmployee(code,false).get());
		return "employee/update";
	}

	@PostMapping("/update/{code}/")
	public String postEmployeeInfoUpdate(@PathVariable(name="code",required = false)String code,@Validated Employee employee,BindingResult res,Model model) {
		String password = "";
		if (res.hasErrors()) {
			model.addAttribute("update_error_message","入力値が不正です。再入力してください。");
			return getEmployeeInfoUpdate(code, model);
		}
		/*
		 * 重複チェックの結果がtrue かつ リクエストURLの社員番号と更新予定の社員番号が異なる際にエラーメッセージを返す
		 * リクエストURLの社員番号と更新予定の社員番号が同じ場合は社員番号以外の変更を行っている可能性があるため、更新処理を実行
		 */
		if (checkDuplicateCode(employee) && !code.equals(employee.getCode())) {
			model.addAttribute("update_error_message", "入力された社員番号は登録済みです。");
			return getEmployeeInfoUpdate(code, model);
		}
		/* 削除済みの社員番号が入力されているかチェックし、結果がtrueの場合はエラーメッセージを返す */
		else if(existDeletedEmployeeCode(employee)) {
			model.addAttribute("update_error_message", "入力された社員番号を持つ従業員は削除されています。別の社員番号を入力してください。");
			return getEmployeeInfoUpdate(code, model);
		}
		else {
//			パスワード欄に入力があれば入力された内容でパスワードを更新、入力がなければDBから既存のパスワードを取得しパスワードを設定する
			if("".equals(employee.getPassword())) {
				password=service.getEmployee(code,false).get().getPassword();
//				パスワードのハッシュ処理はユーザ登録時に行っているため不要
				employee.setPassword(password);
			}
			else {
				System.out.println(employee.getPassword());
				employee.setPassword(passwordEncoder.encode(employee.getPassword()));
			}
			employee.setDeleteFlag(false);
			LocalDateTime dateTime= LocalDateTime.now();
			employee.setCreatedAt(dateTime);
			employee.setUpdatedAt(dateTime);
			service.updateEmployee(employee.getCode(), employee.getName(), employee.getRole(),employee.getPassword(), employee.getUpdatedAt(),false);
		}
		return "redirect:/employee/list";
	}
//　　論理削除
	@GetMapping("delete/{id}/")
	public String deleteEmployee(@PathVariable(name = "id",required = false)String id,Model model) {
		Optional<Employee> employeeInfo = service.getEmployee(id,false);
		Employee employee = employeeInfo.get();
		employee.setDeleteFlag(true);
		service.updateEmployee(employee.getCode(), employee.getName(), employee.getRole(),employee.getPassword(), employee.getUpdatedAt(),true);

		return "redirect:/employee/list";
	}

//	社員番号重複チェック処理
	public boolean checkDuplicateCode(Employee employee) {
		Employee updatedEmployeeCode = null;
//		DB上に検索対象のレコードがみつからなかった場合,falseを返す
		try {
			updatedEmployeeCode = service.getEmployee(employee.getCode(),false).get();
		} catch (Exception e) {
			return false;
		}

//		リクエストURLに設定された社員番号と一致する場合、社員番号以外の項目が変更対象となるためfalseを返す
//		登録予定の社員番号でDB検索を行い結果が取得できたらtrueを返す
		if (updatedEmployeeCode!=null) {
			return true;
		}
		return false;
	}

//	削除済み社員番号チェック処理
	public boolean existDeletedEmployeeCode(Employee employee) {
		Employee duplicateDeletedEmployee = null;
		try {
//			未削除ユーザのレコードが見つからなかった場合は削除済みの従業員に対して再検索を実施
			duplicateDeletedEmployee = service.getEmployee(employee.getCode(), true).get();
		}catch (Exception e) {
			return false;
		}

//		登録予定の社員番号でDB検索を行い結果が取得できたらtrueを返す
		if (duplicateDeletedEmployee != null) {
			return true;
		}

		return false;
	}

//	パスワード空文字チェック処理
	public boolean isEmptyPassword(String password) {
		if("".equals(password)) {
			return true;
		}
		return false;
	}
}
