package com.epam.smvc.service;

import java.util.List;

import com.epam.smvc.model.HiredBook;

import org.springframework.transaction.annotation.Transactional;

public interface HiredBookService {
	
	@Transactional
	boolean save(HiredBook book);

	HiredBook find(Long id);

	List<HiredBook> getBooks();
	
	List<HiredBook> listBooksByUser(String username);
}
