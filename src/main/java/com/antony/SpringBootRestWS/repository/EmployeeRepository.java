/**
 * 
 */
package com.antony.SpringBootRestWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.antony.SpringBootRestWS.dataobject.Employee;

/**
 * @author Antony John
 *
 */
@Repository
@Transactional
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{	

}
