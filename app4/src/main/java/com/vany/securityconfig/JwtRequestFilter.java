package com.vany.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vany.exception.UserServiceException;
import com.vany.service.ErrorMessageService;
import com.vany.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
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
						throw new UserServiceException(ErrorMessageService.NOT_PASS_CORRECT_TOKEN);
					} catch (ExpiredJwtException e) {
						throw new UserServiceException(ErrorMessageService.TOKEN_EXPIRED);
					}
				}

				// Once we get the token validate it.
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

					// if token is valid configure Spring Security to manually set
					// authentication
					if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// After setting the Authentication in the context, we specify
						// that the current user is authenticated. So it passes the
						// Spring Security Configurations successfully.
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}

				// Filtering another request
				filterChain.doFilter(request, response);

		
		
	}

}
