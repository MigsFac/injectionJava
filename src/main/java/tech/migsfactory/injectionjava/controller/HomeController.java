package tech.migsfactory.injectionjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/java")
	public String home(Model model) {
		model.addAttribute("message","Hello!?!");
		
		return "java";
	}
}