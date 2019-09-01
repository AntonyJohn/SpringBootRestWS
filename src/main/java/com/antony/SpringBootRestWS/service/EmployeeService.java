/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.service;

import com.antony.SpringBootRestWS.dataobject.Employee;
import com.antony.SpringBootRestWS.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antony John
 */

@Service
public class EmployeeService {
    
	@Autowired
    private EmployeeRepository employeeRepository;
	
	private static @com.antony.SpringBootRestWS.Log Logger LOG;	
    
    public Optional<Employee> retrieveEmployee(String empId) {
    	LOG.info("EmployeeService --> retrieveEmployee()1");    	
        return employeeRepository.findById(new Integer(empId));                                      
    }
    
    public List<Employee> retrieveAllEmployee() {
    	LOG.info("EmployeeService --> retrieveAllEmployee()");
    	return employeeRepository.findAll();
    }
}
