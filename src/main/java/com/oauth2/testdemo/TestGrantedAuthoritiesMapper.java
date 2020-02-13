package com.oauth2.testdemo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

//@Component
public class TestGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {

	@Override
	public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

		authorities.forEach(authority -> {

			if (OidcUserAuthority.class.isInstance(authority)) {
				OidcUserAuthority oidcAuthority = (OidcUserAuthority) authority;
				// oidcAuthority.getIdToken();
				// oidcAuthority.getUserInfo();
				System.out.println("OIDC Authority: " + oidcAuthority);
				
			} else if (OAuth2UserAuthority.class.isInstance(authority)) {
				OAuth2UserAuthority oAuth2Authority = (OAuth2UserAuthority) authority;
				//Map<String, Object> attributes = oAuth2Authority.getAttributes();

				System.out.println("OAuth2 authority: " + oAuth2Authority);
			}

		});

		return mappedAuthorities;
	}

}
