package com.sciencom.restful.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.sciencom.restful.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.sciencom.restful.models.User user = userRepository.findByUserName(username);
		
		 if(user == null){
	            throw  new  UsernameNotFoundException(username);
	        }

		 
		 return new User(
				 user.getUserName(),
				 user.getPassword(),
				 Collections.emptyList()
				 );
		 
	}

}
