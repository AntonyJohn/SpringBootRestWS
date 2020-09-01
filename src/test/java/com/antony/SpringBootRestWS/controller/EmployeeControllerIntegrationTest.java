/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antony.SpringBootRestWS.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.antony.SpringBootRestWS.LogInjector;
import com.antony.SpringBootRestWS.dataobject.Employee;
import com.antony.SpringBootRestWS.service.EmployeeService;
import com.antony.SpringBootRestWS.utill.Response;

/**
 *
 * @author Antony John
 */

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerIntegrationTest {

	@Mock
	private EmployeeService employeeServiceMock;

	@InjectMocks
	EmployeeController employeeController;

	/**
	 * setupFactory method used to inject @LOG on EmployeeController class 
	 * to avoid null pointer exception
	 */
	@Before
	public void setupFactory() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.addBeanPostProcessor(new LogInjector());
		factory.applyBeanPostProcessorsBeforeInitialization(employeeController, "employeeController");
	}

	@Test
	public void testRetrieveEmployee() throws Exception {
		Response res = new Response();
		Employee antony = new Employee();
		antony.setId(1);
		res.setResponseType("S");
		res.setResponseValue(antony);
		Mockito.when(employeeServiceMock.retrieveEmployee("1")).thenReturn(res);
		Employee emp = (Employee) employeeController.retrieveEmployee("1").getBody().getResponseValue();
		assertEquals(antony.getFirstName(), emp.getFirstName());
	}

	@Test
	public void testretrieveAllEmployee() throws Exception {
		Response res = new Response();
		List<Employee> empList = new ArrayList<Employee>();
		Employee antony = new Employee();
		antony.setId(1);
		antony.setFirstName("antony");
		empList.add(antony);
		Employee john = new Employee();
		antony.setId(2);
		antony.setFirstName("john");
		empList.add(john);
		res.setResponseType("S");
		res.setResponseValue(empList);

		Mockito.when(employeeServiceMock.retrieveAllEmployee()).thenReturn(res);
		List<Employee> emp = (List<Employee>) employeeController.retrieveAllEmployee().getBody().getResponseValue();
		assertEquals(antony.getFirstName(), emp.get(0).getFirstName());
	}	
}