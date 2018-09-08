package com.vn.camthanh.spring.security.oauth2.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/userInfo")
	public Principal userInfo(Principal principal) {
		return principal;
	}
	
}
