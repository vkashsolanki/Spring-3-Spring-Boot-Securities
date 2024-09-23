package com.security.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @EnableMethodSecurity is bydefault true
@Configuration
//@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public UserDetailsService detailsService() {

		// this user class used to withUsername() method here and this single user are
		// created name of nornmal

		UserDetails normalUser = User.withUsername("solanki").password(passwordEncoder().encode("password"))
				.roles("NORMAL").build();

		// admin user are created
		UserDetails adminUser = User.withUsername("solanki1").password(passwordEncoder().encode("password"))
				.roles("ADMIN").build();

		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);

		return detailsManager;
		// new CustomeUSerDetailService se DATABASE se data ko fetch karte iska override
		// method me logic dall ke iska method loadUserbyuserName()

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
/*		httpSecurity.csrf().disable().authorizeHttpRequests()
		   .permitAll()
			.anyRequest().authenticated().and()
					.formLogin()

			 .requestMatchers("/home/public")*/

		// for public
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/home/admin").hasRole("ADMIN")
		.requestMatchers("/home/normal").hasRole("NORMAL")
		.requestMatchers("/home/public").hasRole("public").anyRequest().authenticated().and().formLogin();
		
		
		return httpSecurity.build();

	}

}
