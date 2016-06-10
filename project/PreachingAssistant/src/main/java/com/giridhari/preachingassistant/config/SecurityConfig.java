package com.giridhari.preachingassistant.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from user_account where username=?")
			.authoritiesByUsernameQuery("select username, type from user_account where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/bower_components/**", "/*.js",
						"/*.jsx", "/main.css").permitAll()
				.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
				.anyRequest().authenticated()
				.and()	
			.httpBasic()
				.and()
			.csrf().disable()
			.logout()
				.logoutSuccessUrl("/");
	}
	
}
