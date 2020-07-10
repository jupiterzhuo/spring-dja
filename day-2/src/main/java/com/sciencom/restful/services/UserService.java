package com.sciencom.restful.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sciencom.restful.dto.user.CreateUserDTO;
import com.sciencom.restful.dto.user.ListUserDTO;
import com.sciencom.restful.exceptions.GenericErrorException;
import com.sciencom.restful.models.User;
import com.sciencom.restful.repositories.UserRepository;
import org.modelmapper.TypeToken;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	//Get all
	public List<ListUserDTO> getAllUser(){
		List<User> users= userRepository.findAll();
		Type targetType = new TypeToken<List<ListUserDTO>>() {}.getType();
		return modelMapper.map(users, targetType);
	}
	
	//get By id
	
	public ListUserDTO getUserById(Long id) throws GenericErrorException {
//		User user = userRepository.findById(id)
//				.orElseThrow(
//						()-> 
//							new GenericErrorException("User with id " + id + "Not Found")
//						);
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
		
			throw 	new GenericErrorException("User with id " + id + "Not Found");
		}
		return modelMapper.map(user, ListUserDTO.class);
	}
	
	public User saveUser(CreateUserDTO user) {
		return userRepository.save(modelMapper.map(user, User.class));
	}
	
}
