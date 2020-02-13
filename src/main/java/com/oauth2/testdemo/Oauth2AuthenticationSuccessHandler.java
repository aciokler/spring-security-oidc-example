package com.oauth2.testdemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private RedirectStrategy redirectStrategy;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		//Map<String, Object> attributes = token.getPrincipal().getAttributes();
		String firstname = null, lastname = null, email = null;
		if (token.getPrincipal() instanceof DefaultOidcUser) {
			DefaultOidcUser oidcToken = (DefaultOidcUser) token.getPrincipal();
			firstname = oidcToken.getGivenName();
			lastname = oidcToken.getFamilyName();
			email = oidcToken.getEmail();
		}

		System.out.println(String.format("first: %s, last: %s, email: %s", firstname, lastname, email));

		this.redirectStrategy.sendRedirect(request, response, "/");
	}

//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		if(!this.portfolioService.userHasAportfolio(authentication.getName())) {
//			this.portfolioService.createNewPortfolio(authentication.getName());
//			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
//			Map<String, Object> attributes = token.getPrincipal().getAttributes();
//			String firstname = null, lastname = null, email = null;
//			if(token.getAuthorizedClientRegistrationId().equals("facebook")) {
//				String name = attributes.get("name").toString();
//				firstname = name.split(" ")[0];
//				lastname = name.split(" ")[1];
//				email = attributes.get("email").toString();
//			} else if (token.getPrincipal() instanceof DefaultOidcUser) {	
//				DefaultOidcUser oidcToken = (DefaultOidcUser) token.getPrincipal();
//				firstname = oidcToken.getGivenName();
//				lastname = oidcToken.getFamilyName();
//				email = oidcToken.getEmail();
//			}
//			UserOAuth2Dto user = new UserOAuth2Dto(firstname,lastname,authentication.getName(),email);
//			this.userRegistrationService.registerNewAuth2User(user);
//		}
//		this.redirectStrategy.sendRedirect(request, response, "/portfolio");
//	}

}
