package com.sciencom.restful.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sciencom.restful.dto.auth.LoginDTO;
import com.sciencom.restful.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sciencom.restful.filters.SecurityConstant;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
			HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		//Username & password
		try {
			LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
	
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginDTO.getUsername(),
							loginDTO.getPassword(),
							new ArrayList<>()
							));
		} 
		catch (IOException e) {
			// TODO: handle exception
			 throw new RuntimeException();
		}
	
		
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//Create Token
		
		  String token = JWT.create()
	                .withSubject(
	                        ((User) authResult.getPrincipal())
	                                .getUserName()
	                )
	                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME))
	                .sign(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()));
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(
	                "{\"" + SecurityConstant.HEADER_STRING + "\":\"" + SecurityConstant.TOKEN_PREFIX+ token + "\"}"
	        );
	}


	
	
	
}
