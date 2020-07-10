package com.sciencom.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sciencom.restful.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
