package karolh95.classicmodels.controller.raport;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.EmployeeQuery;
import karolh95.classicmodels.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("employee/raports")
public class EmployeeRaports {

	private final EmployeeService service;

	@GetMapping("summary")
	public ResponseEntity<List<EmployeeQuery.NameJob>> summary() {

		List<EmployeeQuery.NameJob> summary = service.getEmployeesSummaries();
		return ResponseEntity.ok(summary);
	}

	@GetMapping("summary/{jobTitle}/{officeCode}")
	public List<EmployeeQuery.NameJobOffice> summaryOffice(@PathVariable String jobTitle,
			@PathVariable String officeCode) {

		return service.getEmployeeOfficeSummaries(jobTitle, officeCode);
	}

	@GetMapping("officeCodeBetween/{low}/{high}")
	public List<EmployeeQuery.NameJobOffice> officeCodeBetween(@PathVariable String low,
			@PathVariable String high) {

		return service.findEmployeeWhereOfficeCodeBetween(low, high);
	}

	@GetMapping("lastname/{lastName}")
	public List<EmployeeQuery.NameJob> lastNameContaining(@PathVariable String lastName) {
		return service.findEmployeeByLastNameContaining(lastName);
	}

	@GetMapping("withCustomerPayments")
	public List<EmployeeQuery.WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return service.getEmployeesWithCustomersPayments();
	}

	@GetMapping("withCustomerNumber")
	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithCustomersNumbers() {

		return service.getEmployeesWithCustomerNumbers();
	}

	@GetMapping("withoutCustomerNumber")
	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithoutCustomersNumbers() {

		return service.getEmployeesWithoutCustomerNumbers();
	}

	@GetMapping("withReportsTo")
	public List<EmployeeQuery.WithManager> getEmployeesWithReportsTo() {

		return service.getEmployeesWithReportsTo();
	}
}
