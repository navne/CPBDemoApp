package com.sada.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sada.web.model.UserCredential;

public class CPBAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private String passwordMFParameter = "passwordMF";
	
	
	public CPBAuthenticationFilter() {
		super();
	}

	protected String obtainPasswordMF(HttpServletRequest request) {
		return request.getParameter(passwordMFParameter);
	}
	
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String passwordMF = obtainPasswordMF(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		if (passwordMF == null) {
			passwordMF = "";
		}
		username = username.trim();

		UserCredential userCredential = new UserCredential(username, password, passwordMF);
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, userCredential);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
