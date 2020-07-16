package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Office;

public final class EmployeeUtil {

	private static final Long EMPLOYEE_NUMBER_MIN = 1L;
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String EXTENSION = "extension";
	private static final String EMAIL = "email";
	private static final String JOB_TITLE = "jobTitle";

	private static final Long NEW_EMPLOYEE_NUMBER = 2L;
	private static final String NEW_LAST_NAME = "new_lastName";
	private static final String NEW_FIRST_NAME = "new_firstName";
	private static final String NEW_EXTENSION = "new_extension";
	private static final String NEW_EMAIL = "new_email";
	private static final String NEW_JOB_TITLE = "new_jobTitle";

	private static final Long EMPLOYEE_NUMBER_MAX = 6L;

	private EmployeeUtil() {

	}

	public static Employee employee() {

		return employee(EMPLOYEE_NUMBER_MIN);
	}

	private static final Employee employee(Long employeeNumber) {

		Employee employee = new Employee();

		employee.setEmployeeNumber(employeeNumber);
		employee.setLastName(LAST_NAME);
		employee.setFirstName(FIRST_NAME);
		employee.setExtension(EXTENSION);
		employee.setEmail(EMAIL);
		employee.setJobTitle(JOB_TITLE);

		employee.setOffice(OfficeUtil.office());

		return employee;
	}

	public static DtoEmployee dtoNewEmployee() {

		DtoEmployee employee = new DtoEmployee();

		employee.setEmployeeNumber(NEW_EMPLOYEE_NUMBER);
		employee.setLastName(NEW_LAST_NAME);
		employee.setFirstName(NEW_FIRST_NAME);
		employee.setExtension(NEW_EXTENSION);
		employee.setEmail(NEW_EMAIL);
		employee.setJobTitle(NEW_JOB_TITLE);

		return employee;
	}

	public static List<Employee> employees() {

		List<Employee> employees = new ArrayList<>();

		for (Long employeeNumber = EMPLOYEE_NUMBER_MIN; employeeNumber < EMPLOYEE_NUMBER_MAX; employeeNumber++) {
			employees.add(employee(employeeNumber));
		}

		return employees;
	}

	public static void assertEquals(Employee employee, DtoEmployee dtoEmployee) {

		assertNotNull(employee, "Employee should not be null");
		assertNotNull(dtoEmployee, "DTO employee should not be null");

		assertEmployeesNumbersEquals(employee.getEmployeeNumber(), dtoEmployee.getEmployeeNumber());
		assertModifiableFieldsEquals(employee, dtoEmployee);
		assertOfficesCodesEquals(employee.getOffice(), dtoEmployee.getOfficeCode());
	}

	public static void assertUpdated(final Employee original, DtoEmployee dtoEmployee, Employee employee) {

		assertEmployeesNumbersEquals(original.getEmployeeNumber(), employee.getEmployeeNumber());
		assertModifiableFieldsEquals(employee, dtoEmployee);
	}

	private static void assertEmployeesNumbersEquals(Long expected, Long actual) {

		Assertions.assertEquals(expected, actual, "Employee number should match");
	}

	private static void assertModifiableFieldsEquals(Employee employee, DtoEmployee dtoEmployee) {

		Assertions.assertEquals(dtoEmployee.getLastName(), employee.getLastName(), "Last name should match");
		Assertions.assertEquals(dtoEmployee.getFirstName(), employee.getFirstName(), "First name should match");
		Assertions.assertEquals(dtoEmployee.getExtension(), employee.getExtension(), "Extenstion should match");
		Assertions.assertEquals(dtoEmployee.getEmail(), employee.getEmail(), "Email should match");
		Assertions.assertEquals(dtoEmployee.getJobTitle(), employee.getJobTitle(), "Job title should match");
	}

	private static void assertOfficesCodesEquals(Office office, String officeCode) {

		assertNotNull(office, "Office should not be null");
		Assertions.assertEquals(office.getOfficeCode(), officeCode, "Office code should match");
	}
}