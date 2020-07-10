package com.sciencom.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sciencom.restful.models.Student;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {

}
