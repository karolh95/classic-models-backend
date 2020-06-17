package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DTOEmployee;
import karolh95.classicmodels.mapper.EmployeeMapper;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeMapperTests {

	@Autowired
	private EmployeeMapper mapper;

	@Autowired
	private EmployeeRepository repository;

	@Test
	@DisplayName("Should map Employee to Dto")
	public void mapEmployeeToDto() {

		Optional<Employee> optional = this.repository.findById(1056L);

		assertTrue(optional.isPresent(), "Employee should exist");

		Employee employee = optional.get();
		DTOEmployee dto = this.mapper.employeeToDto(employee);

		assertEquals(employee.getEmployeeNumber(), dto.getEmployeeNumber(), "Employee number should match");
		assertEquals(employee.getLastName(), dto.getLastName(), "Last name should match");
		assertEquals(employee.getFirstName(), dto.getFirstName(), "First name should match");
		assertEquals(employee.getExtension(), dto.getExtension(), "Extension shouold match");
		assertEquals(employee.getEmail(), dto.getEmail(), "Email should match");
		assertEquals(employee.getJobTitle(), dto.getJobTitle(), "Job title should match");
		assertEquals(employee.getEmployee().getEmployeeNumber(), dto.getReportsTo(), "Reports to should match");
		assertEquals(employee.getOffice().getOfficeCode(), dto.getOfficeCode(), "Office code should match");
	}
}