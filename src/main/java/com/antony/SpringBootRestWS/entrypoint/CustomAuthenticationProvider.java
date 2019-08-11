/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.entrypoint;

/**
 *
 * @author Antony John
 */
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.antony.SpringBootRestWS.Log;

import java.util.Collection;
 
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private CustomUserDetailsService userService;
 
    private static @Log Logger LOG;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	LOG.info("Start:: CustomAuthenticationProvider --> authenticate()"); 
    	String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        LOG.info("username::"+username+" password ::"+password); 
        SecurityUser secUser = (SecurityUser) userService.loadUserByUsername(username);
        if (secUser == null) {
            throw new BadCredentialsException("Username not found.");
        }
 
        if (!password.equals(secUser.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }         
        Collection<? extends GrantedAuthority> authorities =secUser.getAuthorities();
        LOG.info("End:: CustomAuthenticationProvider --> authenticate()"); 
        return new UsernamePasswordAuthenticationToken(secUser, password, authorities);
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}