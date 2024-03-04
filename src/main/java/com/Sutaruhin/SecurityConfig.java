package com.Sutaruhin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.formLogin(form -> form
					.loginPage("/login")
					.failureUrl("/login?failure")
					.defaultSuccessUrl("/employee/list",true))
			.exceptionHandling(deny -> deny
					.accessDeniedPage("/display-access-denied")
					)
			.logout(logout -> logout
					.logoutSuccessUrl("/login"))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("login/**").permitAll()
					.requestMatchers("/employee/**").hasAuthority("ADMIN")
					.anyRequest().authenticated()
			)
			;

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


