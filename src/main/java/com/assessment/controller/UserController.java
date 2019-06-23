package com.assessment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.User;
import com.assessment.model.UserDto;
import com.assessment.service.UserService;

/**
 * Controller for User CRUD operations having role based access for specific operations
 * 
 * @author Rahul Agrawal
 *
 */

@RestController
public class UserController {

	@Autowired
	UserService userService;

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable(value = "id") long id){
        return userService.findById(id);
    }


    @PostMapping(value="/signup")
    public User createUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/user/{id}")
    public User updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody UserDto userDto){
        return userService.updateUser(userDto, id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value="/user/{id}")
    public void deleteUser(@PathVariable(value = "id") long id){
        userService.deleteUser(id);
    }
}
