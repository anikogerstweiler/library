package com.epam.smvc.service;

import java.util.List;

import com.epam.smvc.model.HiredBook;

public interface HiredBookService {
	
	void save(HiredBook book);

	HiredBook find(Long id);

	List<HiredBook> getBooks();
}
