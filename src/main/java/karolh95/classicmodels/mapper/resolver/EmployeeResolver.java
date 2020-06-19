package karolh95.classicmodels.mapper.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.repository.EmployeeRepository;

@Component
public class EmployeeResolver {

	@Autowired
	private EmployeeRepository repository;

	public Employee map(Long employeeNumber) {

		if (employeeNumber == null) {
			return null;
		}

		Optional<Employee> optional = repository.findById(employeeNumber);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			
			return null;
		}
	}
}