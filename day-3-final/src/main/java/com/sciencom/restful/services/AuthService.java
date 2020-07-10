package com.sciencom.restful.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sciencom.restful.models.User;
import com.sciencom.restful.repositories.UserRepository;

public class AuthService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		
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
