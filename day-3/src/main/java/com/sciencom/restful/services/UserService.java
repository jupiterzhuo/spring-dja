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
import com.sciencom.restful.exceptions.EntityNotFoundException;
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
	
	public ListUserDTO getUserById(Long id) throws EntityNotFoundException {
//		Optional<User> user = userRepository.findById(id);
//		
//		if(user.isPresent()) {
//			throw new GenericErrorException("User Not Found");
//		}
//		
//		return modelMapper.map(user.get(), ListUserDTO.class);
		
		User user = userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException(User.class,"id",id.toString()) );
		return modelMapper.map(user, ListUserDTO.class);
	}
	
	public User saveUser(CreateUserDTO user) {
		return userRepository.save(modelMapper.map(user, User.class));
	}
	
	//Edit User 
	public User  editUser(CreateUserDTO userUpdated, Long id) throws GenericErrorException {
		userRepository.findById(id)
		.orElseThrow(()->new GenericErrorException("User with id " + id + "Not Found"));
		User user =  modelMapper.map(userUpdated, User.class);
		user.setId(id);
		return userRepository.save(user);
	}
	
	
	//delete
	
	public String deleteUser(Long id) throws GenericErrorException {
		return userRepository.findById(id).map(objStudent ->{
			userRepository.delete(objStudent);
			return " Record With id "+  id  + " Success to delete";
		}).orElseThrow(
				()->new GenericErrorException("Student With id :" + id + " Not Found"));

	}
	
}
