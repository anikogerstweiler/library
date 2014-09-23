package com.epam.smvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.epam.smvc.dao.AuthorityRepository;
import com.epam.smvc.dao.UserRepository;
import com.epam.smvc.model.Authority;
import com.epam.smvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService { //UserDetailsService
    
	@Autowired
    private AuthorityRepository authRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Resource
	private PlatformTransactionManager txManager;
    
    @Transactional
    @Override
    public void save(User user) {
    	TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
        userRepository.save(user);
        txManager.commit(status);
    }

    @Transactional
    @Override
    public void save(Authority auth) {
    	TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
        authRepository.save(auth);
        txManager.commit(status);
    }
    

    /**
    * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
    * @param roles {@link String} of roles
    * @return list of granted authorities
    */
    public Set<GrantedAuthority> getGrantedAuthorities(List<Authority> userAuthorities) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Authority authority : userAuthorities){
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return authorities;
    }

	@Override
	public User getUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
