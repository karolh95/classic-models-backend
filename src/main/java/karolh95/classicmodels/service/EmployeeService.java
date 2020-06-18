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

		List<Employee> employees = this.repository.findAll();
		return this.mapper.employeesToDtos(employees);
	}

	public DtoEmployee getEmployee(Long employeeNumber) {

		Optional<Employee> optional = this.repository.findById(employeeNumber);

		if (optional.isEmpty()) {
			return null;
		}

		Employee employee = optional.get();
		return mapper.employeeToDto(employee);
	}
}