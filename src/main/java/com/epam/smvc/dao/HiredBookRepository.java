package com.epam.smvc.dao;

import java.util.List;

import com.epam.smvc.model.HiredBook;

public interface HiredBookRepository {
	
	HiredBook findById(Long id);
	
	List<HiredBook> getHiredBooks();
	
	void save(HiredBook book);
}
