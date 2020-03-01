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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/employee-management")
public class EmployeeController {
    
	private static @Log Logger LOG;	
    
    @Autowired
    private EmployeeService employeeService;
    
    
    /**
     * Get Employee details by empID
     * 
     * @param empID
     * @return
     */
    @GetMapping(value="/employees/{empId}", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody Optional<Employee>  retrieveEmployee(@PathVariable("empId") String empId){
    	LOG.info("Start:: EmployeeController --> retrieveEmployee()");    	
        Optional<Employee> obj=employeeService.retrieveEmployee(empId);
        LOG.info("End:: EmployeeController --> retrieveEmployee()");
        return obj;				 
    }
            
    /**
     * Retrieve All Employee Details
     * 
     * @return
     */
    @GetMapping(value="/employees", headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public@ResponseBody List<Employee>  retrieveAllEmployee(){					
    	LOG.info("Start:: EmployeeController --> retrieveAllEmployee()");  
        List<Employee> obj=employeeService.retrieveAllEmployee(); 
        LOG.info("End:: EmployeeController --> retrieveAllEmployee()");
        return obj;				 
    }
    
    /**
     * Add new employee
     * 
     * @param employee
     * @return
     */
    @PostMapping(value="/employee", headers={"Content-Type=application/json","Accept=application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Employee  addEmployee(@RequestBody Employee employee){					
    	LOG.info("Start:: EmployeeController --> addEmployee() - POST");     
        Employee obj=employeeService.addEmployee(employee);	
        LOG.info("End:: EmployeeController --> addEmployee() - POST");
        return obj;				 
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
    public Employee  updateEmployee(@RequestBody Employee employee){					
    	LOG.info("Start:: EmployeeController --> updateEmployee() - PUT");     
        Employee obj=employeeService.updateEmployee(employee);	
        LOG.info("End:: EmployeeController --> updateEmployee() - PUT");
        return obj;				 
    }
     
    /**
     * Delete the existing employee
     * 
     * @param empId
     */
    @DeleteMapping(value="/employee/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable String empId){					
    	LOG.info("Start:: EmployeeController --> deleteEmployee() - DELETE");     
        employeeService.deleteEmployee(new Integer(empId));	
        LOG.info("End:: EmployeeController --> deleteEmployee() - DELETE");        				 
    }
}
