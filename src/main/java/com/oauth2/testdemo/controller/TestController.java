package com.oauth2.testdemo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.user.model.User;

@RestController
public class TestController {

	@GetMapping("/")
	public String home() {
		return "test";
	}

	@GetMapping("/oidc-principal")
	public OidcUser getOidcUserPrincipal(@AuthenticationPrincipal User principal) {
		System.out.println("printing user email: " + principal.getEmail());
		return principal;
	}

}
