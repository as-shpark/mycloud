package com.iso.mycloud.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.iso.mycloud.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/**").hasRole(Role.USER.name())
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
	}
}
