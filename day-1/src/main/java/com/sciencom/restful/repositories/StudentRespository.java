package com.sciencom.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sciencom.restful.models.Student;

public interface StudentRespository extends JpaRepository<Student, Long> {

}
