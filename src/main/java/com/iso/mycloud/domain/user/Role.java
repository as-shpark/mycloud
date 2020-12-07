package com.iso.mycloud.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
	GUSET("ROLE_GUEST", "guest"), ADMIN("ROLE_ADMIN", "admin"), USER("ROLE_USER", "admin");
	
	private final String key;
	private final String title;
}
