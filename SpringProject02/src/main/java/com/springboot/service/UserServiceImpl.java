package com.springboot.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Role;
import com.springboot.model.User;
import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
	@Override
	public void getSaveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.getByRole("EMPLOYEE"))));
		userRepository.save(user);
		
	}
	
	
	
}
