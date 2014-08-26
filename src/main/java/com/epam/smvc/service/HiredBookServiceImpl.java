package com.epam.smvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.epam.smvc.dao.HiredBookRepository;
import com.epam.smvc.model.HiredBook;

@Service("hiredBookService")
public class HiredBookServiceImpl implements HiredBookService {

	@Autowired
	HiredBookRepository repository;
	
	@Resource
	private PlatformTransactionManager txManager;
	
	@Override
	@Transactional
	public boolean save(HiredBook book) {
		TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
		boolean saved = repository.save(book);
		if (saved) {
			txManager.commit(status);
		} else {
			txManager.rollback(status);
		}
		
		return saved;
	}

	@Override
	public HiredBook find(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<HiredBook> getBooks() {
		return repository.getHiredBooks();
	}

	@Override
	public List<HiredBook> listBooksByUser(String username) {
		return repository.listBooksByUser(username);
	}

}
