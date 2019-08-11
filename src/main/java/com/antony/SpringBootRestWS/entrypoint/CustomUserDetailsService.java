/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.entrypoint;

import com.antony.SpringBootRestWS.Log;
import com.antony.SpringBootRestWS.dataobject.Users;
import com.antony.SpringBootRestWS.service.LoginService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antony John
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginService loginService;
    
    private static @Log Logger LOG;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	LOG.info("Start:: CustomUserDetailsService --> loadUserByUsername()");     
    	Users user = loginService.findUserByName(userName);
	    if(user == null){
	        throw new UsernameNotFoundException("UserName "+userName+" not found");
	    } 
	    LOG.info("End:: CustomUserDetailsService --> loadUserByUsername()");  
	    return new SecurityUser(user);
    }
}
