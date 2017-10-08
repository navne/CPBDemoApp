package com.sada.web.auth;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sada.web.model.UserCredential;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication auth = null;
		String name = authentication.getName();
        UserCredential credentials = (UserCredential) authentication.getCredentials();
        
        System.out.println("Name = " + name + " ,Unix Password = " + credentials.getPassword() + ", MF Password = " + credentials.getPasswordMF());
        
        // use the credentials and authenticate against the third-party system
        if(shouldAuthenticateAgainstThirdPartySystem(name, credentials)){
        	System.out.println("Succesful authentication!");
        	auth = new UsernamePasswordAuthenticationToken(name, credentials, new ArrayList<>());	
        } else {
        	System.out.println("Login fail!");
        	throw new BadCredentialsException("Username/Unix Password/MF Password does not match");
        }
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	private boolean shouldAuthenticateAgainstThirdPartySystem(String name, UserCredential credentials) {
		if((name.equals(credentials.getPasswordMF()) && name.equals(credentials.getPassword())) 
        		|| (name.equals(credentials.getPasswordMF()) && name.equals(credentials.getPassword()))){
        	return true;	
        }
		return false;
	}
}
