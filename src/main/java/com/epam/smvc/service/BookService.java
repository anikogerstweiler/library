package com.epam.smvc.service;

import java.util.List;

import com.epam.smvc.model.Book;

public interface BookService {
	
	void save(Book book);

	Book find(Long id);

	Book findByTitle(String title);

	List<Book> getBooks();

	void removeBookById(Long id);
}
