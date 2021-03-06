package com.iso.mycloud.config.auth.vo;

import lombok.Getter;

@Getter
public class SessionUser {
	private String name;
	private String email;
	private String picture;
	
	public SessionUser(String name, String email, String picture) {
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
}
