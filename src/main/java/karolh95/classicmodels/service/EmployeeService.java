package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.DtoEmployee;
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
}
