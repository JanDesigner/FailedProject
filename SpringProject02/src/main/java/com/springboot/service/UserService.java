package com.springboot.service;

import com.springboot.model.User;

public interface UserService {
	
	public User getUserByEmail(String email);
	public void getSaveUser(User user);
	
}

