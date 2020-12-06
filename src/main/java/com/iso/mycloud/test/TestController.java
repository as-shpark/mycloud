package com.iso.mycloud.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	@GetMapping(value = "/test/hello")
	public String test() {
		return "Hello world!";
	}
}
