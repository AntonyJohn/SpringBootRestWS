/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.antony.SpringBootRestWS.Log;
import com.antony.SpringBootRestWS.dataobject.Employee;
import com.antony.SpringBootRestWS.service.EmployeeService;


/**
 *
 * @author Antony John
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
	private static @Log Logger LOG;	
	Employee emp;
    
    @Autowired
    private EmployeeService employeeService;
    
    
    /**
     * Get Employee details by empID
     * 
     * @param empID
     * @return
     */
    @RequestMapping(value="/retrieveEmployee", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody Optional<Employee>  retrieveEmployee(@RequestParam("empID") String empID){					
    	LOG.info("Start:: EmployeeController --> retrieveEmployee()");    	
        Optional<Employee> obj=employeeService.retrieveEmployee(empID);
        LOG.info("End:: EmployeeController --> retrieveEmployee()");
        return obj;				 
    }
            
    /**
     * Retrieve All Employee Details
     * 
     * @return
     */
    @RequestMapping(value="/retrieveAllEmployee", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody List<Employee>  retrieveAllEmployee(){					
    	LOG.info("Start:: EmployeeController --> retrieveAllEmployee()");  
        List<Employee> obj=employeeService.retrieveAllEmployee(); 
        LOG.info("End:: EmployeeController --> retrieveAllEmployee()");
        return obj;				 
    }
    
    
}
