package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Office;

public class EmployeeUtil {

	public static Employee employee() {

		Employee employee = new Employee();

		employee.setEmployeeNumber(1L);
		employee.setLastName("lastName");
		employee.setFirstName("firstName");
		employee.setExtension("extension");
		employee.setEmail("email");
		employee.setJobTitle("jobTitle");

		employee.setOffice(OfficeUtil.office());

		return employee;
	}

	public static void assertEquals(Employee employee, DtoEmployee dtoEmployee) {

		assertNotNull(employee, "Employee should not be null");
		assertNotNull(dtoEmployee, "DTO employee should not be null");

		Assertions.assertEquals(employee.getEmployeeNumber(), dtoEmployee.getEmployeeNumber(),
				"Employee number should match");
		Assertions.assertEquals(employee.getLastName(), dtoEmployee.getLastName(), "Last name should match");
		Assertions.assertEquals(employee.getFirstName(), dtoEmployee.getFirstName(), "First name should match");
		Assertions.assertEquals(employee.getExtension(), dtoEmployee.getExtension(), "Extension shouold match");
		Assertions.assertEquals(employee.getEmail(), dtoEmployee.getEmail(), "Email should match");
		Assertions.assertEquals(employee.getJobTitle(), dtoEmployee.getJobTitle(), "Job title should match");

		Office office = employee.getOffice();

		assertNotNull(office, "Office should not be null");
		Assertions.assertEquals(office.getOfficeCode(), dtoEmployee.getOfficeCode());
	}
}