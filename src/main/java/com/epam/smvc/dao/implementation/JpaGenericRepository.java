package com.epam.smvc.dao.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class JpaGenericRepository {

	 @PersistenceContext
	 protected EntityManager entityManager;
}
