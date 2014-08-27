package com.epam.smvc.dao.implementation;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.epam.smvc.dao.UserRepository;
import com.epam.smvc.model.User;

@Repository
public class JpaUserRepository extends JpaGenericRepository implements UserRepository {

	@Override
	public User findByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("Select u from User u where u.username like :username", User.class);
		query.setParameter("username", username);
		
		return query.getSingleResult();
	}

	@Override
	public void save(User user) {
		entityManager.persist(user);
	}

}
