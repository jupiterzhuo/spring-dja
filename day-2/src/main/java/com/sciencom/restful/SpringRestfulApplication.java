package com.sciencom.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sciencom.restful.models.Student;
import com.sciencom.restful.repositories.StudentRespository;

@SpringBootApplication
public class SpringRestfulApplication implements CommandLineRunner {

	@Autowired
	private StudentRespository studentRespository;	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestfulApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hello From Console");
		saveData();
//		showData();
//		deleteData();
	}
	
	public void saveData() {
		Student myStudent = new Student();
		myStudent.setName("Jupiter");
		myStudent.setAge(16);
		studentRespository.save(myStudent);
		Student myStudent2 = new Student();
		myStudent2.setName("Davin");
		myStudent2.setAge(20);
		studentRespository.save(myStudent2);
	}
	
	public void showData() {
		List<Student> students = (List<Student>) studentRespository.findAll();
		students.forEach(System.out::println);
	}
	
	public void deleteData() {
		studentRespository.deleteAll();
	}

}
