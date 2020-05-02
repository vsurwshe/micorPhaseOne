package org.auth.service;

import java.util.ArrayList;
import java.util.Random;

import org.domain.entity.user.UserDet;
import org.exception.exec.UserServiceException;
import org.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email)  {
		UserDet user = userRepo.findByUserEmail(email);
		if (user == null) {
			throw new UserServiceException("User Not Found by email " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
				new ArrayList<>());
	}

	public UserDetails loadRefreshUserByUsername(String email)  {
		UserDet user = userRepo.findByUserEmail(email);
		if (user == null) {
			throw new UserServiceException("User Not Found by email " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
				new ArrayList<>());
	}

	public UserDet save(UserDet user) {
		UserDet newUser = new UserDet();
		long verifyCode= new Random().nextInt(900000)+200000;
		newUser.setUserName(user.getUserName());
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUserVeirfyCode(verifyCode);
		newUser.setUserBalance(0.0);
		newUser.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		return userRepo.save(newUser);
	}
}
