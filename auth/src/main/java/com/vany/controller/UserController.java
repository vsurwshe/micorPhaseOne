package com.vany.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.UserServiceException;
import com.vany.model.JwtRequest;
import com.vany.model.UserDet;
import com.vany.model.UserTokenResponse;
import com.vany.securityconfig.JwtTokenUtil;
import com.vany.service.ErrorMessageService;
import com.vany.service.JwtUserDetailsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	// We use for the token related methods
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// We use for the User Related Services
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	// this method give the response with user token take username and passwrod
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> getUserToken(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {
		// Check user passed credtional are correct or not
		authenticate(jwtRequest.getUserEmail(), jwtRequest.getUserPassword());

		// after successfully authencticated we get user details
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUserEmail());

		// after geting user details genrate the token using user informations
		final String userToken = jwtTokenUtil.generateToken(userDetails);

		// after geting user token, we try to genrate refresh token for geting new user
		// token
		final String userRefreshToken = jwtTokenUtil.genrateRefreshToken(userDetails);

		Long tokenTime = System.currentTimeMillis() + (5 * 60) * 1000;

		// after all process doen we bind it to one enity so we can send this enity as
		// output of this service
		UserTokenResponse userTokenResponse = new UserTokenResponse();
		userTokenResponse.setTokenExprieTime(tokenTime);
		userTokenResponse.setUserToken(userToken);
		userTokenResponse.setUserRefreshToken(userRefreshToken);
		// we returning above single bounded entity
		return ResponseEntity.ok(userTokenResponse);

	}

	// this method resgiter the user in database.
	@RequestMapping(value = "/regsiter", method = RequestMethod.POST)
	public ResponseEntity<?> registerUserDetails(@RequestBody UserDet user) {
		return ResponseEntity.ok(jwtUserDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new UserServiceException(ErrorMessageService.USER_LOCKED + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception(ErrorMessageService.INVALID_CREDTIONAL + e.getMessage());
		}
	}

}
