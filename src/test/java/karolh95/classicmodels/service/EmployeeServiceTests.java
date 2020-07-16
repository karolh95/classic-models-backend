package karolh95.classicmodels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.mapper.EmployeeMapper;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTests {

	@MockBean
	private EmployeeRepository repository;

	@Autowired
	private EmployeeMapper mapper;

	@Autowired
	private EmployeeService service;

	@Test
	@DisplayName("Should create new Employee")
	public void createEmployee() {

		when(repository.getOne(anyLong())).thenThrow(new EntityNotFoundException());
		when(repository.save(any(Employee.class))).thenReturn(employee());

		DtoEmployee employee = dtoEmployee();
		DtoEmployee response = service.saveEmployee(employee);

		assertEquals(employee, response, "Employee and response should match");
	}

	@Test
	@DisplayName("Should update Employee")
	public void updateEmployee() {

		when(repository.getOne(anyLong())).thenReturn(employee());
		when(repository.save(any(Employee.class))).thenReturn(modifiedEmployee());

		DtoEmployee employee = modifiedDtoEmployee();
		DtoEmployee response = service.saveEmployee(employee);

		assertEquals(employee, response, "Employee and response should match");

	}

	private Employee employee() {

		Employee employee = new Employee();

		employee.setEmployeeNumber(1L);
		employee.setLastName("lastName");
		employee.setFirstName("firstName");
		employee.setExtension("extension");
		employee.setEmail("email");
		employee.setJobTitle("jobTitle");
		employee.setOffice(office());

		return employee;
	}

	private Office office() {

		Office office = new Office();

		office.setOfficeCode("officeCode");

		return office;
	}

	private DtoEmployee dtoEmployee() {

		return mapper.employeeToDto(employee());
	}

	private Employee modifiedEmployee() {

		Employee employee = employee();

		employee.setLastName("newLastName");
		employee.setFirstName("newFirstName");
		employee.setExtension("newExtension");
		employee.setEmail("newEmail");
		employee.setJobTitle("newJobTitle");

		return employee;
	}

	private DtoEmployee modifiedDtoEmployee() {

		return mapper.employeeToDto(modifiedEmployee());
	}
}