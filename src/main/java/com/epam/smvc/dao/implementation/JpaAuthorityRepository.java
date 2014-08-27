package com.epam.smvc.dao.implementation;

import org.springframework.stereotype.Repository;

import com.epam.smvc.dao.AuthorityRepository;
import com.epam.smvc.model.Authority;

@Repository
public class JpaAuthorityRepository extends JpaGenericRepository implements AuthorityRepository {

	@Override
	public void save(Authority authority) {
		entityManager.persist(authority);
	}

}
