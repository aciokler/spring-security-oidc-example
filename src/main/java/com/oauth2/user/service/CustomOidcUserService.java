package com.oauth2.user.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.oauth2.user.model.User;

public class CustomOidcUserService extends OidcUserService {

	public OidcUser loadUser(OidcUserRequest userRequest) {
		return new User(super.loadUser(userRequest));
	}

}
