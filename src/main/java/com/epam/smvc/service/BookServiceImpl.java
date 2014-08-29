package com.epam.smvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.epam.smvc.dao.BookRepository;
import com.epam.smvc.model.Book;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Resource
	private PlatformTransactionManager txManager;

	@Transactional
	@Override
	public void save(Book book) {
		TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
		bookRepository.save(book);
		txManager.commit(status);
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

	@Transactional
	@Override
	public void removeBookById(Long id) {
		TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
		bookRepository.removeBookById(id);
		txManager.commit(status);
	}

}
