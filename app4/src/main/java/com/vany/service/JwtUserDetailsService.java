package com.vany.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vany.exception.UserServiceException;
import com.vany.model.UserDet;
import com.vany.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDet user = userRepository.findByUserEmail(email);
		if (user == null) {
			throw new UserServiceException("User Not Found by email "+email);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), new ArrayList<>());
	}
	
	public UserDetails loadRefreshUserByUsername(String email) throws UsernameNotFoundException {
		UserDet user = userRepository.findByUserEmail(email);
		if (user == null) {
			throw new UserServiceException("User Not Found by email " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
				new ArrayList<>());
	}
	
	
	public UserDet save(UserDet user) {
		UserDet newUser = new UserDet();
		newUser.setName(user.getName());
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		return userRepository.save(newUser);
	}

}
