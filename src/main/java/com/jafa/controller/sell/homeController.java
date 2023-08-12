package com.jafa.controller.sell;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

	public homeController() {
		System.out.println("HomeController가 생성되었습니다.");
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("home", "시작홈입니다.");
		return "sell/home";
	}
}
