package com.epam.smvc.dao;

import java.util.List;

import com.epam.smvc.model.Book;

public interface BookRepository {
	
	Book findByTitle(String title);
	
	Book findById(Long id);
	
	List<Book> findByAuthor(String author);
	
	List<Book> getBooks();
	
	void save(Book book);

	void removeBookById(Long id);
	
	void updateBook(Book book);
}
