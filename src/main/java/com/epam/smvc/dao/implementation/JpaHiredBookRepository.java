package com.epam.smvc.dao.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.epam.smvc.dao.HiredBookRepository;
import com.epam.smvc.model.HiredBook;

@Repository
public class JpaHiredBookRepository extends JpaGenericRepository implements HiredBookRepository{

	@Override
	public HiredBook findById(Long bookid) {
		TypedQuery<HiredBook> query = entityManager.createQuery("Select h from HiredBook h where h.bookid = :bookid", HiredBook.class);
		query.setParameter("bookid", bookid);
		
		return query.getSingleResult();
	}

	@Override
	public List<HiredBook> getHiredBooks() {
	TypedQuery<HiredBook> query = entityManager.createQuery("Select h from HiredBook h", HiredBook.class);
		
		return query.getResultList();
	}

	@Override
	public void save(HiredBook book) {
		entityManager.persist(book);
	}

}
