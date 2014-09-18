package com.epam.smvc.dao.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	public String save(HiredBook book) {
		String available = isBookAvailable(book.getFromdate(), book.getTodate(), book.getBookid());
		if ("available".equals(available)) {
			entityManager.persist(book);
		}
		
		return available;
	}
	
	private String isBookAvailable(Date from, Date to, Long id) {
		TypedQuery<HiredBook> query = entityManager.createQuery(
				"Select h from HiredBook h where h.bookid = :id and ((h.fromdate between :from and :to) or "
				+ "(h.todate between :from and :to)) order by h.todate", HiredBook.class);
		String fromDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(from);
		String toDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(to);
		System.out.println("FROM " + from + " TO " + to);
		System.out.println("QFROM " + fromDate + " QTO " + toDate);
		
		query.setParameter("from", from)
			 .setParameter("to", to)
			 .setParameter("id", id);
		
		
		List<HiredBook> result = query.getResultList();
		
		if (result.size() == 0) {
			return "available";
		}
		
		return result.get(result.size() - 1).getTodate().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HiredBook> listBooksByUser(String username) {
		Query query = entityManager.createQuery("Select h, b from HiredBook h, Book b where h.bookid = b.id and username like :username order by h.todate");
		query.setParameter("username", username);
		
		return query.getResultList();
	}

}
