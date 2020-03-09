package org.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.auth.securityconfig.JwtTokenUtil;
import org.auth.service.JwtUserDetailsService;
import org.domain.component.JwtRequest;
import org.domain.component.UserTokenResponse;
import org.domain.entity.UserDet;
import org.exception.exec.UserServiceException;
import org.service.apiService.LogService;
import org.service.apiService.ResponseEntityResult;
import org.service.apiService.ErrorServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	// This getting User Token Form database
	@PostMapping(value = "/token")
	public ResponseEntity<?> findUserToken(@Valid @RequestBody JwtRequest userRequest) {
		return this.getUserToken(userRequest);
	}

	// This method save the user into database
	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUserDetails(@Valid @RequestBody UserDet user) {
		return this.registerUserDetails(user);
	}

	// This method get the refresh token form database
	@PostMapping(value = "/refreshToken")
	public ResponseEntity<?> findRefreshToken(HttpServletRequest userRequest) {
		return this.findRefreshToken(userRequest);
	}

	// this method give the response with user token take user name and password
	public ResponseEntity<?> getUserToken(@Valid @RequestBody JwtRequest jwtRequest) {
		UserTokenResponse userResult = null;
		try {
			// Check user passed credtional are correct or not
			authenticate(jwtRequest.getUserEmail(), jwtRequest.getUserPassword());
			// after successfully authenticated we get user details
			final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUserEmail());
			Long tokenTime = System.currentTimeMillis() + (5 * 60) * 1000;
			// we returning above single bounded entity
			userResult = this.setUserTokenResponse(tokenTime, userDetails);
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userResult);
	}

	// this method resgiter the user in database.
	public ResponseEntity<?> registerUserDetails(@RequestBody UserDet user) {
		UserDet userResult = null;
		try {
			userResult = jwtUserDetailsService.save(user);
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userResult);
	}

	// this method take refresh token and provide to user fresh token with new
	// refresh token
	public ResponseEntity<?> getUserRefreshToken(HttpServletRequest userReuest) throws Exception {
		UserTokenResponse userResult = null;
		try {
			// this line get refresh token form user request named as refresh
			String refreshToken = userReuest.getHeader("refresh");
			// this line get user name from refresh token
			String username = jwtTokenUtil.getUsernameFromToken(refreshToken);
			// form user name we find the user details
			final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
			Long tokenTime = System.currentTimeMillis() + (5 * 60 * 60) * 1000;
			// we returning above single bounded entity
			userResult = this.setUserTokenResponse(tokenTime, userDetails);
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.badRequest(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(userResult);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			LogService.setLogger(ErrorServiceMessage.USER_LOCKED + " " + e.getMessage());
			throw new UserServiceException(ErrorServiceMessage.USER_LOCKED + e.getMessage());
		} catch (BadCredentialsException e) {
			LogService.setLogger(ErrorServiceMessage.INVALID_CREDTIONAL + " " + e.getMessage());
			throw new Exception(ErrorServiceMessage.INVALID_CREDTIONAL + e.getMessage());
		}
	}

	// ---------- Private Methods
	private UserTokenResponse setUserTokenResponse(Long tokenTime, UserDetails userDetails) throws Exception {
		UserTokenResponse userTokenResponse = null;
		try {
			// after getting user token, we try to generate token for getting new user
			final String userToken = jwtTokenUtil.generateToken(userDetails);
			// after getting user token, we try to generate refresh token for getting new
			// user token
			final String userRefreshToken = jwtTokenUtil.genrateRefreshToken(userDetails);
			userTokenResponse = new UserTokenResponse();
			userTokenResponse.setTokenExprieTime(tokenTime);
			userTokenResponse.setUserToken(userToken);
			userTokenResponse.setUserRefreshToken(userRefreshToken);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			throw new Exception(ErrorServiceMessage.NOT_VALID_USER + " " + e.getMessage());
		}
		return userTokenResponse;
	}
}
