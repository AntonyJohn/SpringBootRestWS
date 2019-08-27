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
		Employee antony = new Employee();
		antony.setId(1);		
		Mockito.when(employeeServiceMock.retrieveEmployee("1")).thenReturn(Optional.of(antony));
		assertEquals("test jenkins", employeeController.retrieveEmployee("1").get().getFirstName());
	}

	@Test
	public void testretrieveAllEmployee() throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		Employee antony = new Employee();
		antony.setId(1);
		antony.setFirstName("antony");
		empList.add(antony);
		Employee john = new Employee();
		antony.setId(2);
		antony.setFirstName("john");
		empList.add(john);

		Mockito.when(employeeServiceMock.retrieveAllEmployee()).thenReturn(empList);
		assertEquals(antony.getFirstName(), employeeController.retrieveAllEmployee().get(0).getFirstName());
	}	
}