package com.Sutaruhin.controller;



import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	PasswordEncoder passwordEncoder;

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
		Optional<Employee> testEmployee =service.getEmployee(code);
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
	public String postRegister(Employee employee) {
		employee.setDeleteFlag(false);
		LocalDateTime dateTime= LocalDateTime.now();
		employee.setCreatedAt(dateTime);
		employee.setUpdatedAt(dateTime);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		service.saveEmployee(employee);
		return "redirect:/employee/list";
	}
//  編集
	@GetMapping("/update/{code}/")
	public String getEmployeeInfoUpdate(@PathVariable(name="code",required = false)String code,Model model) {
		model.addAttribute("title","社員番号："+code+"の従業員情報 編集ページ");
		model.addAttribute("employee",service.getEmployee(code).get());
		return "employee/update";
	}

	@PostMapping("/update/{id}/")
	public String postEmployeeInfoUpdate(@PathVariable(name="id",required = false)String code,Employee employee) {
		String password = "";
		employee.setDeleteFlag(false);
//		パスワード欄に入力があれば入力された内容でパスワードを更新、入力がなければDBから既存のパスワードを取得しパスワードを設定する
		if(employee.getPassword()==null) {
			password=service.getEmployee(code).get().getPassword();
//			パスワードのハッシュ処理はユーザ登録時に行っているため不要
			employee.setPassword(password);
		}else {
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		}
		LocalDateTime dateTime= LocalDateTime.now();
		employee.setCreatedAt(dateTime);
		employee.setUpdatedAt(dateTime);
		service.saveEmployee(employee);
		return "redirect:/employee/list";
	}
//	1.ここに編集ページに遷移したときのGET,POSTのメソッドを準備するDONE
//	2.ユーザ編集ページのHTML(テンプレート)を作成する
//	3.detail.htmlにユーザ編集ページへのリンクを追加する

//　　論理削除

}
