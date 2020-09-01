/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.controller;

import java.util.List;
import java.util.Optional;

//import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.antony.SpringBootRestWS.Log;
import com.antony.SpringBootRestWS.Base.exception.ServiceException;
import com.antony.SpringBootRestWS.dataobject.Employee;
import com.antony.SpringBootRestWS.service.EmployeeService;
import com.antony.SpringBootRestWS.utill.Response;


/**
 *
 * @author Antony John
 */

@RestController
@RequestMapping("/employee-management")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class EmployeeController {
    
	private static @Log Logger LOG;	
    
    @Autowired
    private EmployeeService employeeService;
    
    
    /**
     * Get Employee details by empID
     * 
     * @param empID
     * @return
     * @throws ServiceException 
     */
    @GetMapping(value="/employees/{empId}", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response>  retrieveEmployee(@PathVariable("empId") String empId) throws ServiceException{
    	LOG.info("Start:: EmployeeController --> retrieveEmployee()");    	
    	Response response = employeeService.retrieveEmployee(empId);
    	if ("F".equals(response.getResponseType())) {
			return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
    	LOG.info("End:: EmployeeController --> retrieveEmployee()");
		return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);                        		
    }
            
    /**
     * Retrieve All Employee Details
     * 
     * @return
     * @throws ServiceException 
     */
    @GetMapping(value="/employees", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response>  retrieveAllEmployee() throws ServiceException{					
    	LOG.info("Start:: EmployeeController --> retrieveAllEmployee()");  
        Response response=employeeService.retrieveAllEmployee(); 
        if ("F".equals(response.getResponseType())) {
			return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
        LOG.info("End:: EmployeeController --> retrieveAllEmployee()");
    	return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);   				
    }
    
    /**
     * Add new employee
     * 
     * @param employee
     * @return
     */
    @PostMapping(value="/employee", headers={"Content-Type=application/json","Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)    
    public ResponseEntity<Response>  addEmployee(@RequestBody Employee employee){					
    	LOG.info("Start:: EmployeeController --> addEmployee() - POST");     
        Response response=employeeService.addEmployee(employee); 
        if ("F".equals(response.getResponseType())) {
			return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
        LOG.info("End:: EmployeeController --> addEmployee() - POST");
    	return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);                 			
    }
    
    /**
     * Update the existing employee
     * 
     * @param employee
     * @return
     */
    @PutMapping(value="/employee", headers={"Content-Type=application/json","Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Response>  updateEmployee(@RequestBody Employee employee){					
    	LOG.info("Start:: EmployeeController --> updateEmployee() - PUT");     
        Response response=employeeService.updateEmployee(employee); 
        if ("F".equals(response.getResponseType())) {
			return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
        LOG.info("End:: EmployeeController --> updateEmployee() - PUT");
    	return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);        			
    }
     
    /**
     * Delete the existing employee
     * 
     * @param empId
     */
    @DeleteMapping(value="/employee/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response> deleteEmployee(@PathVariable String empId){					
    	LOG.info("Start:: EmployeeController --> deleteEmployee() - DELETE");    
    	Response response = new Response();
        employeeService.deleteEmployee(new Integer(empId));	
        response.setResponseType("S");
        response.setResponseValue("Employee deleted sucessfully");
        LOG.info("End:: EmployeeController --> deleteEmployee() - DELETE"); 
        return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);               				
    }
}
