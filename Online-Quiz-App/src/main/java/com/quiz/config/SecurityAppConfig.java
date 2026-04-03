package com.quiz.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityAppConfig {

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain settingUpHttpSecurity(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(customizer -> {

			customizer.requestMatchers("/register", "/WEB-INF/**").permitAll().requestMatchers("/admin/**")
					.hasRole("ADMIN").requestMatchers("/user/**").hasAnyRole("USER", "ADMIN").anyRequest()
					.authenticated();
		});

		http.formLogin(formLogin -> formLogin.loginPage("/login").successHandler(new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
					response.sendRedirect("admin/dashboard");
				} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
					response.sendRedirect("user/home");
				} else {
					response.sendRedirect("login");
				}
			}
		}).failureUrl("/login?error=true").permitAll());

		http.logout(logout -> logout.logoutSuccessUrl("/login")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll());

		http.httpBasic();

		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Bean
	HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}

}
