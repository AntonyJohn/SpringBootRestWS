/**
 * 
 */
package com.antony.SpringBootRestWS.controller;


import com.antony.SpringBootRestWS.dataobject.JwtResponse;
import com.antony.SpringBootRestWS.dataobject.Users;
import com.antony.SpringBootRestWS.entrypoint.CustomUserDetailsService;
import com.antony.SpringBootRestWS.entrypoint.SecurityUser;
import com.antony.SpringBootRestWS.login.valueobject.UsersVO;
import com.antony.SpringBootRestWS.security.config.JwtTokenUtil;
import com.antony.SpringBootRestWS.service.LoginService;
import com.antony.SpringBootRestWS.Log;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Antony John
 *
 */
@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;
    private static @Log Logger LOG;
    Users users;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService userService;
    
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    
	/**
	 * Spring Security JWT Authentication
	 * 
	 * @param loginid
	 * @return
	 */
    @SuppressWarnings({ "rawtypes" })
    @PostMapping(value="/authenticatejwt", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<JwtResponse>  authenticateJWT(@RequestBody UsersVO loginid){					
    	LOG.info("Start:: LoginController --> authenticate() - POST");     
        try {
			authenticate(loginid.getUsername(), loginid.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return ResponseEntity.ok(new JwtResponse(""));	
		}
        //SecurityUser secUser = (SecurityUser) userService.loadUserByUsername(username);
        final UserDetails userDetails = userService.loadUserByUsername(loginid.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        LOG.info("End:: LoginController --> authenticate() - POST");
        return ResponseEntity.ok(new JwtResponse(token));			 
    }
    
    /**
	 * Spring Security JWT Authentication
	 * 
	 * @param loginid
	 * @return
	 *
    @SuppressWarnings({ "rawtypes" })
    @PostMapping(value="/authenticatejwt", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object>  authenticateJWT(@RequestBody UsersVO loginid){					
    	LOG.info("Start:: LoginController --> authenticate() - POST"); 
    	Response response = new Response();
        try {
			authenticate(loginid.getUsername(), loginid.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			LinkedHashMap<String, String> message = new LinkedHashMap<String, String>();
			message.put("message", "INVALID_CREDENTIALS");
			response.setResponseValue(message);
			response.setResponseType("F");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			//return ResponseEntity.ok(new JwtResponse(""));	
		}
        //SecurityUser secUser = (SecurityUser) userService.loadUserByUsername(username);
        final UserDetails userDetails = userService.loadUserByUsername(loginid.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        LOG.info("End:: LoginController --> authenticate() - POST");
        response.setResponseValue(new JwtResponse(token));
      	response.setResponseType("S");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
        //return ResponseEntity.ok(new JwtResponse(token));			 
    }
    */
    /**
     * Spring Security Basic Authentication
     * 
     * @param loginid
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    @PostMapping(value="/authenticatebasic", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List  authenticateBasic(@RequestBody UsersVO loginid){					
    	LOG.info("Start:: LoginController --> authenticate() - POST");     
        List obj=loginService.getAuthenticate(loginid);	
        LOG.info("End:: LoginController --> authenticate() - POST");
        return obj;				 
    }
    
    //Retrieve User Details	
    @GetMapping(value="/user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Users>  retrieveUser(@RequestParam("userID") String userID){					
    	LOG.info("Start:: LoginController --> retrieveUser() - GET");        
        Optional<Users> obj=loginService.findUserByID(new Integer(userID)); 
        LOG.info("End:: LoginController --> retrieveUser() - GET");
        return obj;				 
    }
    
    @SuppressWarnings({ "rawtypes" })
    @GetMapping(value="/userall")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List  retrieveAllUser(){					
    	LOG.info("Start:: LoginController --> retrieveAllUser() - GET");        
        List<Users> obj=loginService.findAllUser(); 
        LOG.info("End:: LoginController --> retrieveAllUser() - GET");
        return obj;				 
    }
    /*//Create User	
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value="/user", method=RequestMethod.POST, headers={"Content-Type=application/json","Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody List  user(@RequestBody UsersVO loginvo){					
    	LOG.info("Start:: LoginController --> user() - POST");
        users=new Users();
        users.setUsername(loginvo.getUsername());
        users.setPassword(loginvo.getPassword());        
        users.setEnabled((short)1);
        users.setId(loginvo.getId());        
        List obj=loginService.createUser(users);	
        LOG.info("Start:: LoginController --> user() - POST");
        return obj;				 
    }
    
    
    
    //Update User	
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value="/user", method=RequestMethod.PUT, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody List  updateUser(@RequestBody UsersVO loginvo){					
    	LOG.info("Start:: LoginController --> updateUser() - PUT");
        users=new Users();
        users.setUsername(loginvo.getUsername());      
        users.setPassword(loginvo.getPassword());
        users.setEnabled((short)1);
        users.setId(1);        
        List obj=loginService.updateUser(users);
        LOG.info("End:: LoginController --> updateUser() - PUT");
        return obj;				 
    }
    
    //Delete User	
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value="/user", method=RequestMethod.DELETE, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody List  removeUser(@RequestBody UsersVO loginvo){					
    	LOG.info("Start:: LoginController --> removeUser() - DELETE");
        users=new Users();
        users.setId(loginvo.getId());        							
        List obj=loginService.removeUser(users);
        LOG.info("End:: LoginController --> removeUser() - DELETE");
        return obj;				 
    }   
    
    public static String encrypt(String source) {
        String md5 = null;
        try {
        MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
        mdEnc.update(source.getBytes(), 0, source.length());
        md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
        } catch (Exception ex) {
        return null;
        }
        return md5;
    }*/


	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
