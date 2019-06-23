package com.assessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.User;

/**
 * Interface extend from JpaRepository to perform user CRUD operations
 * 
 * @author Rahul Agrawal
 *
 */

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	User findByEmail(String email);
}
