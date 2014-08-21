package com.epam.smvc.dao.implementation;

import org.springframework.stereotype.Repository;

import com.epam.smvc.dao.UserRepository;
import com.epam.smvc.model.User;

@Repository
public class JpaUserRepository extends JpaGenericRepository implements UserRepository {

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
