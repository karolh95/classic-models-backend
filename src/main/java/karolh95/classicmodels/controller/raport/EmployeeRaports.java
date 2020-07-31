package karolh95.classicmodels.controller.raport;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Employee;
import karolh95.classicmodels.dto.query.EmployeeQuery;
import karolh95.classicmodels.service.raport.EmployeeRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Employee.RAPORT)
public class EmployeeRaports {

	private final EmployeeRaport raport;

	@GetMapping(Employee.Raport.SUMMARY)
	public ResponseEntity<List<EmployeeQuery.NameJob>> summary() {

		List<EmployeeQuery.NameJob> summary = raport.getEmployeesSummaries();
		return ResponseEntity.ok(summary);
	}

	@GetMapping(Employee.Raport.BY_JOB_OFFICE)
	public List<EmployeeQuery.NameJobOffice> summaryOffice(@PathVariable String jobTitle,
			@PathVariable String officeCode) {

		return raport.getEmployeeOfficeSummaries(jobTitle, officeCode);
	}

	@GetMapping(Employee.Raport.OFFICECODE_BETWEEN)
	public List<EmployeeQuery.NameJobOffice> officeCodeBetween(@PathVariable String low,
			@PathVariable String high) {

		return raport.findEmployeeWhereOfficeCodeBetween(low, high);
	}

	@GetMapping(Employee.Raport.LASTNAME)
	public List<EmployeeQuery.NameJob> lastNameContaining(@PathVariable String lastName) {
		return raport.findEmployeeByLastNameContaining(lastName);
	}

	@GetMapping(Employee.Raport.CUSTOMER_PAYMENTS)
	public List<EmployeeQuery.WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return raport.getEmployeesWithCustomersPayments();
	}

	@GetMapping(Employee.Raport.WITH_CUSTOMER)
	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithCustomersNumbers() {

		return raport.getEmployeesWithCustomerNumbers();
	}

	@GetMapping(Employee.Raport.WITHOUT_CUSTOMER)
	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithoutCustomersNumbers() {

		return raport.getEmployeesWithoutCustomerNumbers();
	}

	@GetMapping(Employee.Raport.WITH_REPORTSTO)
	public List<EmployeeQuery.WithManager> getEmployeesWithReportsTo() {

		return raport.getEmployeesWithReportsTo();
	}
}
