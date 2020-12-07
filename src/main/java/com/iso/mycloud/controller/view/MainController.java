package com.iso.mycloud.controller.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iso.mycloud.config.auth.vo.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	private final HttpSession httpSession;
	
	@GetMapping("/view/main")
	public String hello(Model model) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		
		if(user != null) {
			model.addAttribute("userName", user.getName());
		}
		
		return "main";
	}
}
