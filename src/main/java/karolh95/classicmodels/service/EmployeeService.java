package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.dto.query.EmployeeQuery;
import karolh95.classicmodels.mapper.EmployeeMapper;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeMapper mapper;
	private final EmployeeRepository repository;

	public List<DtoEmployee> getAllEmployees() {

		List<Employee> employees = repository.findAll();
		return mapper.employeesToDtos(employees);
	}

	public DtoEmployee getEmployee(Long employeeNumber) {

		Optional<Employee> optional = repository.findById(employeeNumber);

		if (optional.isEmpty()) {
			return null;
		}

		Employee employee = optional.get();
		return mapper.employeeToDto(employee);
	}

	public DtoEmployee saveEmployee(DtoEmployee dtoEmployee) {

		Employee employee = getOne(dtoEmployee.getEmployeeNumber());

		mapper.updateFromDto(dtoEmployee, employee);

		if (!employee.hasValidIds()) {
			return null;
		}

		employee = repository.save(employee);

		return mapper.employeeToDto(employee);
	}

	private Employee getOne(Long employeeNumber) {

		Optional<Employee> optional = repository.findById(employeeNumber);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			Employee employee = new Employee();

			employee.setEmployeeNumber(employeeNumber);

			return employee;
		}
	}

	public List<EmployeeQuery.NameJobTitle> getEmployeesSummaries() {

		return repository.findAllBy();
	}

	public List<EmployeeQuery.NameJobTitleOffice> getEmployeeOfficeSummaries(String jobTitle,
			String officeCode) {

		TypedSort<Employee> employee = Sort.sort(Employee.class);

		Sort sortByOfficeCode = employee.by(Employee::getOfficeCode).ascending();
		Sort sortByJobTitle = employee.by(Employee::getJobTitle).ascending();

		Sort sort = sortByOfficeCode.and(sortByJobTitle);

		return repository.findByJobTitleOrOfficeCode(jobTitle, officeCode, sort);
	}

	public List<EmployeeQuery.NameJobTitleOffice> findEmployeeWhereOfficeCodeBetween(String low,
			String high) {

		TypedSort<Employee> employee = Sort.sort(Employee.class);

		Sort byOfficeCode = employee.by(Employee::getOfficeCode).ascending();
		Sort byLastName = employee.by(Employee::getLastName).ascending();

		Sort sort = byOfficeCode.and(byLastName);

		return repository.findByOfficeCodeBetween(low, high, sort);
	}

	public List<EmployeeQuery.NameJobTitle> findEmployeeByLastNameContaining(String lastName) {

		return repository.findByLastNameContaining(lastName);
	}

	public List<EmployeeQuery.WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return repository.findAllByOrderByCustomers_CustomerNameAscCustomers_Payments_CheckNumber();
	}
}
