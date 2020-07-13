package karolh95.classicmodels.mappers;

import static karolh95.classicmodels.utils.EmployeeUtil.assertEquals;
import static karolh95.classicmodels.utils.EmployeeUtil.assertUpdated;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.mapper.EmployeeMapper;
import karolh95.classicmodels.mapper.EmployeeMapperImpl;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.mapper.resolver.OfficeResolver;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.utils.EmployeeUtil;
import karolh95.classicmodels.utils.OfficeUtil;

public class EmployeeMapperTests {

	@Mock
	private EmployeeResolver employeeResolver;

	@Mock
	private OfficeResolver officeResolver;

	@InjectMocks
	private EmployeeMapper mapper = new EmployeeMapperImpl();

	@Nested
	@DisplayName("employeeToDto tests")
	class EmployeeToDtoTests {

		@Test
		@DisplayName("Should not map null Employee")
		void mapNullEmployeeToDto() {

			Employee employee = null;

			DtoEmployee dtoEmployee = mapper.employeeToDto(employee);

			assertNull(dtoEmployee, "Dto employee should be null");
		}

		@Test
		@DisplayName("Should map Employee to Dto")
		public void mapEmployeeToDto() {

			Employee employee = EmployeeUtil.employee();

			DtoEmployee dtoEmployee = mapper.employeeToDto(employee);

			assertEquals(employee, dtoEmployee);
		}
	}

	@Nested
	@DisplayName("employeesToDtos tests")
	class EmployeesToDtosTests {

		List<Employee> employees;

		@BeforeEach
		void init() {
			employees = EmployeeUtil.employees();
		}

		@Test
		@DisplayName("Should not map null employees")
		void mapNullEmployeesToDtos() {

			employees = null;

			List<DtoEmployee> dtoEmployees = mapper.employeesToDtos(employees);

			assertNull(dtoEmployees, "Dto employees should be null");
		}

		@Test
		@DisplayName("Should map empty employees")
		void mapEmptyEmployeesToDtos() {

			employees.clear();

			List<DtoEmployee> dtoEmployees = mapper.employeesToDtos(employees);

			assertTrue(dtoEmployees.isEmpty(), "Dto employees should be empty");
		}

		@Test
		@DisplayName("Should map employees")
		void mapEmployeesToDtos() {

			List<DtoEmployee> dtoEmployees = mapper.employeesToDtos(employees);

			for (int i = 0; i < employees.size(); i++) {

				Employee employee = employees.get(i);
				DtoEmployee dtoEmployee = dtoEmployees.get(i);

				assertEquals(employee, dtoEmployee);
			}
		}
	}

	@Nested
	@DisplayName("updateFromDto tests")
	class UpdateFromDtoTests {

		final Employee original = EmployeeUtil.employee();
		Employee employee;
		DtoEmployee dtoEmployee;

		@BeforeEach
		void init() {

			MockitoAnnotations.initMocks(EmployeeMapperTests.this);

			doReturn(EmployeeUtil.employee()).when(employeeResolver).map(anyLong());
			doReturn(OfficeUtil.office()).when(officeResolver).map(anyString());

			employee = EmployeeUtil.employee();
			dtoEmployee = EmployeeUtil.dtoNewEmployee();
		}

		@Test
		@DisplayName("Should not update Employee from null dto")
		void updateEmployeeFromNullDto() {

			dtoEmployee = null;

			mapper.updateFromDto(dtoEmployee, employee);

			assertEquals(original, employee, "Employee should not change");
		}

		@Test
		@DisplayName("Should update Employee from dto")
		void updateEmployeeFromDto() {

			mapper.updateFromDto(dtoEmployee, employee);

			assertUpdated(original, dtoEmployee, employee);
		}
	}
}