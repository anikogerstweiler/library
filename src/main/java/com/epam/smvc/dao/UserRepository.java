package com.epam.smvc.dao;

import com.epam.smvc.model.User;

public interface UserRepository { //extends JpaRepository<User, Long>

	User findByUsername(String username);
    
}
