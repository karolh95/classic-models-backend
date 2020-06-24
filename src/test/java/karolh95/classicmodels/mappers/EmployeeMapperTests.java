package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.mapper.EmployeeMapper;
import karolh95.classicmodels.mapper.EmployeeMapperImpl;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.mapper.resolver.OfficeResolver;
import karolh95.classicmodels.model.Employee;

public class EmployeeMapperTests {

	@Spy
	private EmployeeResolver employeeResolver;

	@Spy
	private OfficeResolver officeResolver;

	@InjectMocks
	private EmployeeMapper mapper = new EmployeeMapperImpl();

	@Test
	@DisplayName("Should map Employee to Dto")
	public void mapEmployeeToDto() {

		MockitoAnnotations.initMocks(this);
		doReturn(null).when(employeeResolver).map(anyLong());
		doReturn(null).when(officeResolver).map(anyString());
		Employee employee = employee();
		
		DtoEmployee dto = mapper.employeeToDto(employee);

		assertEquals(employee.getEmployeeNumber(), dto.getEmployeeNumber(), "Employee number should match");
		assertEquals(employee.getLastName(), dto.getLastName(), "Last name should match");
		assertEquals(employee.getFirstName(), dto.getFirstName(), "First name should match");
		assertEquals(employee.getExtension(), dto.getExtension(), "Extension shouold match");
		assertEquals(employee.getEmail(), dto.getEmail(), "Email should match");
		assertEquals(employee.getJobTitle(), dto.getJobTitle(), "Job title should match");
	}

	private Employee employee() {

		Employee employee = new Employee();

		employee.setEmployeeNumber(1L);
		employee.setLastName("lastName");
		employee.setFirstName("firstName");
		employee.setExtension("extension");
		employee.setEmail("email");
		employee.setJobTitle("jobTitle");

		return employee;
	}
}