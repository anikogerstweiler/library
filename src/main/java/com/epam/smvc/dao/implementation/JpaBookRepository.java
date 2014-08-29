package com.epam.smvc.dao.implementation;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.epam.smvc.dao.BookRepository;
import com.epam.smvc.model.Book;

@Repository
public class JpaBookRepository extends JpaGenericRepository implements BookRepository {

	@Override
	public Book findByTitle(String title) {
		TypedQuery<Book> query = entityManager.createQuery("Select b from Book b where b.title like :title", Book.class);
		query.setParameter("title", title);
		
		return query.getSingleResult();
	}

	@Override
	public List<Book> findByAuthor(String author) {
		TypedQuery<Book> query = entityManager.createQuery("Select b from Book b where b.author like :author", Book.class);
		query.setParameter("author", author);
		
		return query.getResultList();
	}

	@Override
	public List<Book> getBooks() {
		TypedQuery<Book> query = entityManager.createQuery("Select b from Book b", Book.class);
		
		return query.getResultList();
	}

	@Override
	public void save(Book book) {
		entityManager.persist(book);
	}

	@Override
	public Book findById(Long id) {
		return entityManager.find(Book.class, id);
	}

	@Override
	public void removeBookById(Long id) {
		Query removeBookQuery = entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id ");
		removeBookQuery.setParameter("id", id);
		Query removeBookFromHiredBook = entityManager.createQuery("DELETE FROM HiredBook h WHERE h.bookid = :id ");
		removeBookFromHiredBook.setParameter("id", id);
		
		removeBookFromHiredBook.executeUpdate();
		removeBookQuery.executeUpdate();
	}
}
