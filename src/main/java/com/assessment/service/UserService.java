package com.assessment.service;

import com.assessment.model.User;
import com.assessment.model.UserDto;

public interface UserService {

	User createUser(UserDto userDto);
	
	User updateUser(UserDto userDto, long id);
	
	void deleteUser(long id);
	
	User findById(long id);
}
