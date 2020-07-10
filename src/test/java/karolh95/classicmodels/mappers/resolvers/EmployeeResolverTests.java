package karolh95.classicmodels.mappers.resolvers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.repository.EmployeeRepository;
import karolh95.classicmodels.utils.EmployeeUtil;

class EmployeeResolverTests {

	@Mock
	EmployeeRepository repository;

	@InjectMocks
	EmployeeResolver resolver;

	Long employeeNumber;
	Employee employee;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		employeeNumber = 1L;
	}

	@Test
	@DisplayName("Should not map null employeeNumber")
	void mapNullEmployeeNumber() {

		employeeNumber = null;

		employee = resolver.map(employeeNumber);

		assertNull(employee, "Employee should be null");
	}

	@Test
	@DisplayName("Should not map employeeNumber when Employee not exists")
	void mapEmployeeNumberToNonExistingEmployee() {

		when(repository.findById(anyLong())).thenReturn(empty());

		employee = resolver.map(employeeNumber);

		assertNull(employee, "Employee should be null");
	}

	@Test
	@DisplayName("Should map employeeNumber when Employee exists")
	void mapEmployeeNumberToExistingEmployee() {

		when(repository.findById(anyLong())).thenReturn(optional());

		employee = resolver.map(employeeNumber);

		assertNotNull(employee, "Employee should not be null");
	}

	private Optional<Employee> empty() {
		return Optional.empty();
	}

	private Optional<Employee> optional() {
		return Optional.of(EmployeeUtil.employee());
	}
}