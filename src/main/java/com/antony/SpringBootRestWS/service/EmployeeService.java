/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.service;

import com.antony.SpringBootRestWS.dataobject.Employee;
import com.antony.SpringBootRestWS.repository.EmployeeRepository;
import com.antony.SpringBootRestWS.utill.Response;

import java.util.LinkedHashMap;
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
    
    public Response retrieveEmployee(String empId) {
    	LOG.info("EmployeeService --> retrieveEmploye()");  
    	Response response = null;
    	Optional<Employee> employee = employeeRepository.findById(new Integer(empId)); 
    	if(employee.isPresent()) {
    		response = new Response();
    		response.setResponseType("S");
    		response.setResponseValue(employee);
    	} else {
	    	response = new Response();
			response.setResponseType("F");
			LinkedHashMap<String, String> errorMap = new LinkedHashMap<String, String>();
			errorMap.put("message", "No Data Found");
			response.setResponseValue(errorMap);
    	}
        return response;                                      
    }
    
    public Response retrieveAllEmployee() {
    	LOG.info("EmployeeService --> retrieveAllEmployee()");
    	Response response = null;
    	List<Employee> employee = employeeRepository.findAll(); 
    	if(!employee.isEmpty()) {
    		response = new Response();
    		response.setResponseType("S");
    		response.setResponseValue(employee);
    	} else {
	    	response = new Response();
			response.setResponseType("F");
			LinkedHashMap<String, String> errorMap = new LinkedHashMap<String, String>();
			errorMap.put("message", "No Data Found");
			response.setResponseValue(errorMap);
    	}
        return response;    	
    }
    
    public Response addEmployee(Employee emp) {
    	LOG.info("EmployeeService --> addEmployee()");
    	Response response = null;
    	Employee savedEmployee = employeeRepository.saveAndFlush(emp);
    	if(null != savedEmployee) {
    		response = new Response();
    		response.setResponseType("S");
    		response.setResponseValue(savedEmployee);
    	} else {
	    	response = new Response();
			response.setResponseType("F");
			LinkedHashMap<String, String> errorMap = new LinkedHashMap<String, String>();
			errorMap.put("message", "Employee insertion failed");
			response.setResponseValue(errorMap);
    	}
    	return response;
    }
    
    public Response updateEmployee(Employee emp) {
    	LOG.info("EmployeeService --> addEmployee()");
    	Response response = null;
    	Employee savedEmployee = employeeRepository.saveAndFlush(emp);
    	if(null != savedEmployee) {
    		response = new Response();
    		response.setResponseType("S");
    		response.setResponseValue(savedEmployee);
    	} else {
	    	response = new Response();
			response.setResponseType("F");
			LinkedHashMap<String, String> errorMap = new LinkedHashMap<String, String>();
			errorMap.put("message", "Employee insertion failed");
			response.setResponseValue(errorMap);
    	}
    	return response;
    }
    
    public void deleteEmployee(Integer empId) {
    	LOG.info("EmployeeService --> deleteEmployee()");
    	employeeRepository.deleteById(empId);
    }
}
