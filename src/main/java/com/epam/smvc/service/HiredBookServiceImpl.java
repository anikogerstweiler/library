package com.epam.smvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.smvc.dao.HiredBookRepository;
import com.epam.smvc.model.HiredBook;

@Service("hiredBookService")
public class HiredBookServiceImpl implements HiredBookService {

	@Autowired
	HiredBookRepository repository;
	
	@Override
	public void save(HiredBook book) {
		repository.save(book);
	}

	@Override
	public HiredBook find(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<HiredBook> getBooks() {
		return repository.getHiredBooks();
	}

}
