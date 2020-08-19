package karolh95.classicmodels.service.raport;

import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.projection.employee.NameJob;
import karolh95.classicmodels.dto.projection.employee.NameJobOffice;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNameAndPayments;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNumber;
import karolh95.classicmodels.dto.projection.employee.WithManager;
import karolh95.classicmodels.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeRaport {

	private final EmployeeRepository repository;

	public List<NameJob> getEmployeesSummaries() {

		return repository.findEmployees();
	}

	public List<NameJobOffice> getEmployeeOfficeSummaries(String jobTitle, String officeCode) {

		return repository.findByJobOrOffice(jobTitle, officeCode);
	}

	public List<NameJobOffice> findEmployeeWhereOfficeCodeBetween(String low, String high) {

		return repository.findByOfficeCodeBetween(low, high);
	}

	public List<NameJob> findEmployeeByLastNameContaining(String lastName) {

		return repository.findByLastNameContaining(lastName);
	}

	public List<WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return repository.findEmployeePayments();
	}

	public List<WithCustomerNumber> getEmployeesWithCustomerNumbers() {

		return repository.findEmployeesWithCustomers();
	}

	public List<WithCustomerNumber> getEmployeesWithoutCustomerNumbers() {

		return repository.findEmployeesWithoutCustomers();
	}

	public List<WithManager> getEmployeesWithReportsTo() {

		return repository.findEmployeesOrderByEmployeeName();
	}
}
