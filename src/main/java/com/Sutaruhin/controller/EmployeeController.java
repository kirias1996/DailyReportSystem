package com.Sutaruhin.controller;



import java.time.LocalDateTime;
import java.util.Optional;
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

		model.addAttribute("title","id："+code+"の従業員情報 詳細ページ");
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
		service.registEmployee(employee);
		return "redirect:/employee/list";
	}
//  編集
//　　論理削除

}
