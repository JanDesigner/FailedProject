package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long>{
	
	public User getUserByEmail(String email);
	
}
