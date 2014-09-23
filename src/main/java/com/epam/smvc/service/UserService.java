package com.epam.smvc.service;

import com.epam.smvc.model.Authority;
import com.epam.smvc.model.User;

public interface UserService {
	
	void save(User user);

	void save(Authority auth);
	
	User getUserByUserName(String username);
}
