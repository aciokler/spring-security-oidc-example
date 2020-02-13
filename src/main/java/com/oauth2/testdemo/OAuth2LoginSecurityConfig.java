package com.oauth2.testdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import com.oauth2.user.service.CustomOidcUserService;

@Configuration
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Oauth2AuthenticationSuccessHandler oauth2authSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Set<String> googleScopes = new HashSet<>();
		// googleScopes.add("https://www.googleapis.com/auth/userinfo.email");
		// googleScopes.add("https://www.googleapis.com/auth/userinfo.profile");

		// OidcUserService googleUserService = new CustomOidcUserService();
		// googleUserService.setAccessibleScopes(googleScopes);

		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.oauth2Login(oauthLogin -> {
					oauthLogin.userInfoEndpoint().oidcUserService(new CustomOidcUserService());
					oauthLogin.successHandler(oauth2authSuccessHandler);
				});
	}
	
	@Bean
	public RedirectStrategy getRedirectStrategy() {
		return new DefaultRedirectStrategy();
	}
}