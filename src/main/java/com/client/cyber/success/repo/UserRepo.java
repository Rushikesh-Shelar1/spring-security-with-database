package com.client.cyber.success.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.cyber.success.model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
	
	Users findByUsername(String username);

}
