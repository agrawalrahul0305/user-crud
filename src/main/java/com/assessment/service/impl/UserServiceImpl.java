package com.assessment.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.assessment.dao.UserDao;
import com.assessment.error.UserAlreadyExistException;
import com.assessment.model.User;
import com.assessment.model.UserDto;
import com.assessment.service.UserService;

/**
 * Service class to perform user CRUD operations
 * 
 * @author Rahul Agrawal
 *
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}
	
	public User createUser(UserDto userDto) {
		
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with email address: " + userDto.getEmail());
        }
	    User user = new User();
	    user.setUsername(userDto.getUsername());
	    user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
	    user.setFirstName(userDto.getFirstName());
	    user.setLastName(userDto.getLastName());
	    user.setEmail(userDto.getEmail());
	    user.setRoles(userDto.getRoles());
        return userDao.save(user);
	}
	
	public User updateUser(UserDto userDto, long id) {
		
		User existingUser = findById(id);
		existingUser.setUsername(userDto.getUsername());
		existingUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setLastName(userDto.getLastName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setRoles(userDto.getRoles());
        return userDao.save(existingUser);
	}
	
	@Override
	public void deleteUser(long id) {
		
		userDao.deleteById(id);
	}

	@Override
	public User findById(long id) {
		
		return userDao.findById(id).get();
	}

    private boolean emailExists(final String email) {
        return userDao.findByEmail(email) != null;
    }
}
