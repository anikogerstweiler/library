package com.epam.smvc.service;

import com.epam.smvc.model.Authority;
import com.epam.smvc.model.User;

public interface UserService {
	
	void save(User user);
//
//	User find(Long id);
//
//	User findByUsername(String username);

	void save(Authority auth);
}
