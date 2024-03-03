package com.Sutaruhin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class DailyReportsListController {
	@GetMapping("/")
	public String getList(Model model) {
		return "DailyReportsList";
	}
}
