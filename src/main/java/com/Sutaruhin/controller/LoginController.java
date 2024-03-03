//package com.Sutaruhin.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//
//@Controller
//public class LoginController {
//	@GetMapping("/login")
//	public String loginForm() {
//		return "auth/loginForm";
//	}
//
//	@GetMapping(value = "/login",params = "failure")
//	public String loginFail(Model model) {
//		model.addAttribute("failureMessage","ログインに失敗しました");
//		return "auth/loginForm";
//	}
//
////	アクセスエラー画面を表示するハンドラメソッド
//	@RequestMapping("/display-access-denied")
//	public String accessDenied() {
//		return "auth/accessDeny";
//	}
//
//}
