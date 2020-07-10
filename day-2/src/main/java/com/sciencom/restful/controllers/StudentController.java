package com.sciencom.restful.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciencom.restful.repositories.StudentRespository;
import com.sciencom.restful.exceptions.GenericErrorException;
import com.sciencom.restful.models.Student;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class StudentController {
	@Autowired
	private StudentRespository studentRepository;

	//Get All

	@GetMapping("student")
	public List<Student> getAllStudent() {
		return (List<Student>) studentRepository.findAll();
		//return "Get All Student";
	}
	//Get By Id
	@GetMapping("student/{id}")
	public Student getStudentById(@PathVariable Long id) throws GenericErrorException {
		Optional<Student> student=studentRepository.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		}
		else {
			throw new GenericErrorException("Student With id :" + id + " Not Found");
		}

	}
	//Save
	@PostMapping("student")
	//@RequestMapping(value= {"students"}, method = RequestMethod.POST)
	public Student addNewStudent(@Valid@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	//edit
	@PutMapping("student/{id}")
	public Student updateStudent(@Valid@RequestBody Student student, 
			@PathVariable Long id) throws GenericErrorException {
		//find id
		return studentRepository.findById(id).map((objStudent) ->{
			objStudent.setName(student.getName());
			objStudent.setAge(student.getAge());
			return studentRepository.save(objStudent);
		}).orElseThrow(
				()->new GenericErrorException("Student With id :" + id + " Not Found"));

	}
	
	//delete
	
	@DeleteMapping("student/{id}")
	public String deleteStudent(@PathVariable Long id) throws GenericErrorException {
		//find id
		return studentRepository.findById(id).map((objStudent) ->{
			studentRepository.delete(objStudent);
			return " Record With id "+  id  + " Success to delete";
		}).orElseThrow(
				()->new GenericErrorException("Student With id :" + id + " Not Found"));

	}
	
}
