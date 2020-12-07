package com.iso.mycloud.controller.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iso.mycloud.config.auth.vo.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {
	private final HttpSession httpSession;
	
	@GetMapping("/")
	public String main(Model model) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		return user != null ? "redirect:/view/main" : "login";
	}
}
