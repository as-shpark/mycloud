package com.iso.mycloud.controller.view;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	private final HttpSession httpSession;
	
	@GetMapping("/view/main")
	public String hello(Model model) {
		Enumeration<?> attributeNames = httpSession.getAttributeNames();
//		while (attributeNames.hasMoreElements()) {

//			String name = (String) attributeNames.nextElement();
//			if (name.equals("SPRING_SECURITY_CONTEXT")) {
//				SecurityContext value = (SecurityContext) httpSession.getAttribute(name);
//				Authentication authentication = value.getAuthentication();
//				WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
//				String username = authentication.getName();
//				String password = (String) authentication.getCredentials();
//
//				System.out.println("name = " + name + " , value = " + value.toString());
//				System.out.println("authentication : " + authentication.toString());
//				System.out.println("details : " + details.toString());
//				System.out.println("username : " + username);
//				System.out.println("password : " + password);
//
//			}
//
//		}
//
//
//
//		if(user != null) {
//			model.addAttribute("userName", user.getName());
//		}
		
		return "main";
	}
}
