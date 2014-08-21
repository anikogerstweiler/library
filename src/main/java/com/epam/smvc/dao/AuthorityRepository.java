package com.epam.smvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.smvc.model.Authority;
import com.epam.smvc.model.User;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    
    
}
