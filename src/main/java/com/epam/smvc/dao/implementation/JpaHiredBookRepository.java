package com.epam.smvc.dao.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
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
	public Date save(HiredBook book) {
		Date notAvailableTill = isBookAvailable(book.getFromdate(), book.getTodate(), book.getBookid());
		if (notAvailableTill == null) {
			entityManager.persist(book);
		}
		
		return notAvailableTill;
	}
	
	//TODO: not works properly!!
	private Date isBookAvailable(Date from, Date to, Long id) {
		TypedQuery<HiredBook> query = entityManager.createQuery("Select h from HiredBook h where h.id = :id and ((h.fromdate between :from and :to) or (h.todate between :from and :to)) order by h.todate", HiredBook.class);
		query.setParameter("from", from)
			 .setParameter("to", to)
			 .setParameter("id", id);
		
		List<HiredBook> result = query.getResultList();
		System.out.println("SIZE: " + result.size());
		
		System.out.println("TILL: " + result.get(result.size()-1).getTodate());
		
		return result.get(result.size()-1).getTodate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HiredBook> listBooksByUser(String username) {
		Query query = entityManager.createQuery("Select h, b from HiredBook h, Book b where h.bookid = b.id and username like :username");
		query.setParameter("username", username);
		
		return query.getResultList();
	}

}
