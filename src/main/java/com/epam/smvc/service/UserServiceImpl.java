package com.epam.smvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.smvc.dao.AuthorityRepository;
import com.epam.smvc.dao.UserRepository;
import com.epam.smvc.model.Authority;
import com.epam.smvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService { //UserDetailsService
//    @Autowired
//    private AuthorityRepository authRepository;
    
//    @Autowired
//    private UserRepository userRepository;
    
//    @Override
//    public User find(Long id) {
//        return userRepository.findOne(id);
//    }
//
//    @Override
//    public User save(User user) {
//        return userRepository.save(user);
//    }

//    @Override
//    public Authority save(Authority auth) {
//        return authRepository.save(auth);
//    }
    
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//    /**
//    * Returns a populated {@link UserDetails} object.
//    * The username is first retrieved from the database and then mapped to
//    * a {@link UserDetails} object.
//    */
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        
//        User domainUser = userRepository.findByUsername(username);
//        if(domainUser == null){
//            throw new UsernameNotFoundException(username);
//        }
//        User result = new User();
//        result.setUsername(domainUser.getUsername());
//        result.setPassword(domainUser.getPassword());
//        result.setEnabled(true);
//        result.setAccountNonExpired(true);
//        result.setAccountNonLocked(true);
//        result.setCredentialsNonExpired(true);
//        result.setAuthorities(getGrantedAuthorities(domainUser.getEntityAuthorities()));
//        
//        result.setFirstname(domainUser.getFirstname());
//        result.setLastname(domainUser.getLastname());
//
//        return result;
//    }
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

}
