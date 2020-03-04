package org.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.exception.UserServiceException;
import org.auth.securityconfig.JwtRequestFilter;
import org.auth.securityconfig.JwtTokenUtil;
import org.auth.service.ErrorMessageService;
import org.auth.service.JwtUserDetailsService;
import org.auth.service.LogService;
import org.domain.model.JwtRequest;
import org.domain.model.UserDet;
import org.domain.model.UserTokenResponse;
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
		Long tokenTime = System.currentTimeMillis() + (5 * 60) * 1000;
		// we returning above single bounded entity
		return ResponseEntity.ok(this.setUserTokenResponse(tokenTime,userDetails));
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
			LogService.setLogger(ErrorMessageService.USER_LOCKED+" "+e.getMessage());
			throw new UserServiceException(ErrorMessageService.USER_LOCKED + e.getMessage());
		} catch (BadCredentialsException e) {
			LogService.setLogger(ErrorMessageService.INVALID_CREDTIONAL+" "+e.getMessage());
			throw new Exception(ErrorMessageService.INVALID_CREDTIONAL + e.getMessage());
		}
	}

	// this method take refresh token and provide to user fresh token with new
	// refresh token
	@RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
	public ResponseEntity<?> getUserRefreshToken(HttpServletRequest userReuest) throws Exception {
		// this line get refresh token form user request named as refresh
		String refreshToken = userReuest.getHeader("refresh");
		// this line get username from refresh token
		String username = jwtTokenUtil.getUsernameFromToken(refreshToken);
		// form username we find the user details
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
		Long tokenTime = System.currentTimeMillis() + (5 * 60 * 60 ) * 1000;
		// we returning above single bounded entity
		return ResponseEntity.ok(this.setUserTokenResponse(tokenTime,userDetails));
	}
	//---------- Private Methods
	private UserTokenResponse setUserTokenResponse(Long tokenTime, UserDetails userDetails) throws Exception {
		UserTokenResponse userTokenResponse=null;
		try {
			// after geting user token, we try to genrate token for geting new user
			final String userToken = jwtTokenUtil.generateToken(userDetails);
			// after geting user token, we try to genrate refresh token for geting new user token
			final String userRefreshToken = jwtTokenUtil.genrateRefreshToken(userDetails);
			userTokenResponse = new UserTokenResponse();
			userTokenResponse.setTokenExprieTime(tokenTime);
			userTokenResponse.setUserToken(userToken);
			userTokenResponse.setUserRefreshToken(userRefreshToken);
		} catch (Exception e) {
			LogService.setLogger(e.getMessage());
			throw new Exception(ErrorMessageService.NOT_VALID_USER+" "+e.getMessage());
		}
		return userTokenResponse;
	}
}
