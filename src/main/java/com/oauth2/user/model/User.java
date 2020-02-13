package com.oauth2.user.model;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class User extends DefaultOidcUser {

	private static final long serialVersionUID = -4237645652587179756L;

	public User(OidcUser user) {
		super(user.getAuthorities(), user.getIdToken());
	}

}
