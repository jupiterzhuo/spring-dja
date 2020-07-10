package com.sciencom.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sciencom.restful.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
