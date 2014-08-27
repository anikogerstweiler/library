package com.epam.smvc.dao;

import com.epam.smvc.model.User;

public interface UserRepository { 
	
	User findByUsername(String username);
	
	void save(User user);
    
}
