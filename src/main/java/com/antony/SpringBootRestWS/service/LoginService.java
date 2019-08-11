/**
 * 
 */
package com.antony.SpringBootRestWS.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antony.SpringBootRestWS.dataobject.Users;
import com.antony.SpringBootRestWS.login.valueobject.UsersVO;
import com.antony.SpringBootRestWS.repository.UserRepository;
import com.antony.SpringBootRestWS.Log;

/**
 * @author Antony John
 * 
 */
@Service
public class LoginService {

	@Autowired
    private UserRepository userRepository;
	
	private static @Log Logger LOG;	
        
	/**
	 * 
	 * @param loginvo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getAuthenticate(UsersVO loginvo) {
		LOG.info("LoginService --> getAuthenticate()");				
		List userList = userRepository.findByUserNameAndPassword(loginvo.getUsername(), loginvo.getPassword());
		return userList;
	}
         
	/**
	 * Find the user by name
	 * 
	 * @param userName
	 * @return
	 */
	public Users findUserByName(String userName) {
		LOG.info("LoginService --> findUserByName()"); 
		return userRepository.findByUserName(userName);                      
	} 
	
	/**
	 * Find the user by id
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Users> findUserByID(Integer id) {
		LOG.info("LoginService --> findUserByName()"); 
		return userRepository.findById(id);                      
	} 
	
	/**
	 * Find All User
	 * 
	 * @return
	 */
	public List<Users> findAllUser() {
		LOG.info("LoginService --> userAll()"); 
		return userRepository.findAll();                      
	} 
}
