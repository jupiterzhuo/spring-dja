package com.sciencom.restful.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sciencom.restful.models.Student;
import com.sciencom.restful.repositories.StudentRespository;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRespository studentRespository;
	
	//get All Student
	@GetMapping("student")
//	@RequestMapping(method = RequestMethod.GET,value = "student")
	public List<Student> getAllStudent(){
		return (List<Student>) studentRespository.findAll();
	}
	
	//get student by id
	@GetMapping("student/{id}")
	public Student getAStudentById(@PathVariable Long id){
		Optional<Student> student = studentRespository.findById(id);

		return student.get();

	}
}
