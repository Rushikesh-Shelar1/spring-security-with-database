package com.client.cyber.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
//	
//	@GetMapping("/hello")
//	public String sayHello() {
//		return "Hello";
//	} 
//	
//	@PreAuthorize("hasRole('USER')")
//	@GetMapping("/user")
//	public String userEndoint() {
//		return "Hello ,User!!";
//	}
//	
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/admin")
//	public String adminEndpoint() {
//		return "Hello ,Adminn!";
//	}
	
	@GetMapping("/home")
	public String home() {
		return "This is Home page";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "This is Admin Page";
		
	}

}
