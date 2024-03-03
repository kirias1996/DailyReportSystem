//package com;
//
//import org.antlr.v4.runtime.atn.SemanticContext.AND;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	@SuppressWarnings("removal")
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		http
//			.authorizeHttpRequests()
//			.requestMatchers(HttpMethod.POST,"/login/**").hasAnyRole("管理者","一般")
//			.requestMatchers("/employee/**").hasAnyRole("管理者","一般")
//			.anyRequest().permitAll()
//		.and()
//			.formLogin()
//			.loginPage("/login")
//			.failureUrl("/login?failure")
//			.defaultSuccessUrl("/employee/list")
//		.and()
//			.exceptionHandling()
//			.accessDeniedPage("/display-access-denied")
//		.and()
//			.logout()
//			.logoutSuccessUrl("/login");
//
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}


