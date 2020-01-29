package com.vany.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vany.exception.UserServiceException;
import com.vany.service.ErrorMessage;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//	1) Geting Token Form Request
		final String requestTokenHeader = request.getHeader("Authorization");
		
		//	Declare the local Vairiable
		String username = null;
		String jwtToken = null;
		
		// Filter the JWT Token from Bearer, get username and jwt token seperate  
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				throw new UserServiceException(ErrorMessage.NOT_PASS_CORRECT_TOKEN);
			} catch (ExpiredJwtException e) {
				throw new UserServiceException(ErrorMessage.TOKEN_EXPIRED);
			}
		}
		
		// Filtering another request		
		filterChain.doFilter(request, response);
		
		
	}

}
