package com.sciencom.restful.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sciencom.restful.filters.SecurityConstant;
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	 private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
	        String token  = request.getHeader(SecurityConstant.HEADER_STRING);
	        if (token!=null){
	            String user = JWT.require(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()))
	                    .build()
	                    .verify(token.replace(SecurityConstant.TOKEN_PREFIX,""))
	                    .getSubject();
	            if(user!=null){
	                return  new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
	            }
	            return  null;
	        }
	        return  null;
	    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 String header = request.getHeader(SecurityConstant.HEADER_STRING);
	        if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
	            chain.doFilter(request, response);
	            return;
	        }
	        UsernamePasswordAuthenticationToken authenticationToken =getAuthentication(request);
	        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	        chain.doFilter(request,response);
		
	}
	 
	 
}
