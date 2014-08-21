package com.epam.smvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.smvc.dao.BookRepository;
import com.epam.smvc.model.Book;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public Book find(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Book findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}

	@Override
	public List<Book> getBooks() {
		return bookRepository.getBooks();
	}

}
