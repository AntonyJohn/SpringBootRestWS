/**
 * 
 */
package com.antony.SpringBootRestWS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.antony.SpringBootRestWS.dataobject.Users;

/**
 * @author Antony John
 *
 */
@Repository
@Transactional
//@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository  extends JpaRepository<Users, Integer>{
	
	// Ref Named query in Users.java
    List<Users> findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);	
    Users findByUserName(@Param("username") String username);
}
