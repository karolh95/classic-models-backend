package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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

	public static DtoEmployee dtoNewEmployee() {

		DtoEmployee employee = new DtoEmployee();

		employee.setEmployeeNumber(2L);
		employee.setLastName("new_lastName");
		employee.setFirstName("new_firstName");
		employee.setExtension("new_extension");
		employee.setEmail("new_email");
		employee.setJobTitle("new_jobTitle");

		return employee;
	}

	public static List<Employee> employees() {

		List<Employee> employees = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			employees.add(employee());
		}

		return employees;
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

	public static void assertUpdated(final Employee original, DtoEmployee dtoEmployee, Employee employee) {

		Assertions.assertEquals(original.getEmployeeNumber(), employee.getEmployeeNumber(),
				"Employee number should match");
		Assertions.assertEquals(dtoEmployee.getLastName(), employee.getLastName(), "Last name should match");
		Assertions.assertEquals(dtoEmployee.getFirstName(), employee.getFirstName(), "First name should match");
		Assertions.assertEquals(dtoEmployee.getExtension(), employee.getExtension(), "Extenstion should match");
		Assertions.assertEquals(dtoEmployee.getEmail(), employee.getEmail(), "Email should match");
		Assertions.assertEquals(dtoEmployee.getJobTitle(), employee.getJobTitle(), "Job title should match");
	}
}