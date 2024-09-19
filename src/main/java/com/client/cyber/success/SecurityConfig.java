package com.client.cyber.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((requests)->requests.anyRequest().authenticated());
		//http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		 UserDetails user1=User.withUsername("user1")
//				 .password("{noop}password1")
//				 .roles("USER")
//				 .build();
//		 
//		 UserDetails admin=User.withUsername("admin")
//				 .password("{noop}adminPass")
//				 .roles("ADMIN")
//				 .build();
//		 
//		 JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);
//		 
//		 userDetailsManager.createUser(user1);
//		 userDetailsManager.createUser(admin);
//		 return userDetailsManager;
//		 
//		//return new InMemoryUserDetailsManager(user1,admin);
//	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.requestMatchers("/")
		.permitAll()
		.requestMatchers("/home")
		.hasAuthority("USER")
		.requestMatchers("/admin")
		.hasAuthority("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
	}



}
