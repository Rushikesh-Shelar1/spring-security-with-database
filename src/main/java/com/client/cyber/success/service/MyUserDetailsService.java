package com.client.cyber.success.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.client.cyber.success.model.UserPrincipal;
import com.client.cyber.success.model.Users;
import com.client.cyber.success.repo.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=repo.findByUsername(username);
		if (user==null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException(" User Not Found ");
		}
		return new UserPrincipal(user);
	}

}
