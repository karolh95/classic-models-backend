package karolh95.classicmodels.service.raport;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.query.EmployeeQuery;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeRaport {

	private final EmployeeRepository repository;

	public List<EmployeeQuery.NameJob> getEmployeesSummaries() {

		return repository.findAllBy();
	}

	public List<EmployeeQuery.NameJobOffice> getEmployeeOfficeSummaries(String jobTitle,
			String officeCode) {

		TypedSort<Employee> employee = Sort.sort(Employee.class);

		Sort sortByOfficeCode = employee.by(Employee::getOfficeCode).ascending();
		Sort sortByJobTitle = employee.by(Employee::getJobTitle).ascending();

		Sort sort = sortByOfficeCode.and(sortByJobTitle);

		return repository.findByJobTitleOrOfficeCode(jobTitle, officeCode, sort);
	}

	public List<EmployeeQuery.NameJobOffice> findEmployeeWhereOfficeCodeBetween(String low,
			String high) {

		TypedSort<Employee> employee = Sort.sort(Employee.class);

		Sort byOfficeCode = employee.by(Employee::getOfficeCode).ascending();
		Sort byLastName = employee.by(Employee::getLastName).ascending();

		Sort sort = byOfficeCode.and(byLastName);

		return repository.findByOfficeCodeBetween(low, high, sort);
	}

	public List<EmployeeQuery.NameJob> findEmployeeByLastNameContaining(String lastName) {

		return repository.findByLastNameContaining(lastName);
	}

	public List<EmployeeQuery.WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return repository.findAllByOrderByCustomers_CustomerNameAscCustomers_Payments_CheckNumber();
	}

	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithCustomerNumbers() {

		return repository.findAllByCustomers_CustomerNumberIsNotNullOrderByEmployeeNumber();
	}

	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithoutCustomerNumbers() {

		return repository.findAllByCustomers_CustomerNumberIsNullOrderByEmployeeNumber();
	}

	public List<EmployeeQuery.WithManager> getEmployeesWithReportsTo() {

		return repository.findAllByOrderByEmployee_LastNameAscEmployee_FirstName();
	}
}
