package com.sciencom.restful.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciencom.restful.dto.user.CreateUserDTO;
import com.sciencom.restful.dto.user.ListUserDTO;
import com.sciencom.restful.exceptions.GenericErrorException;
import com.sciencom.restful.models.User;
import com.sciencom.restful.services.UserService;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("user")
	public ResponseEntity<List<ListUserDTO>> getAllUser() {
		List<ListUserDTO> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<ListUserDTO> getAllUser(@PathVariable Long id) throws GenericErrorException {
		ListUserDTO user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping("user")
	public ResponseEntity<CreateUserDTO> saveUser(@Valid@RequestBody CreateUserDTO userDto) {
		userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
	}
	
}
