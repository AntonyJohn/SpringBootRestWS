/**
 * 
 */
package com.antony.SpringBootRestWS.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import com.antony.SpringBootRestWS.dataobject.Employee;

/**
 * @author Antony John
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeRepositoryIntegrationTest {

	@Mock
	private EmployeeRepository employeeRepositoryMock;

	@Test
	public void testRetrieveEmployee() {
		Employee antony = new Employee();
		antony.setId(1);
		antony.setFirstName("antony");
		Mockito.when(employeeRepositoryMock.findById(antony.getId())).thenReturn(Optional.of(antony));
		assertEquals(antony.getFirstName(), employeeRepositoryMock.findById(antony.getId()).get().getFirstName());
	}

	@Test
	public void testRetrieveAllEmployee() {
		List<Employee> empList = new ArrayList<Employee>();
		Employee antony = new Employee();
		antony.setId(1);
		antony.setFirstName("antony");
		empList.add(antony);
		Employee john = new Employee();
		antony.setId(2);
		antony.setFirstName("john");
		empList.add(john);

		Mockito.when(employeeRepositoryMock.findAll()).thenReturn(empList);
		assertEquals(empList.get(0).getFirstName(), employeeRepositoryMock.findAll().get(0).getFirstName());
	}
}
