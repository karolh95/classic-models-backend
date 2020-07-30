package karolh95.classicmodels.controller.raport;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.EmployeeQuery;
import karolh95.classicmodels.service.raport.EmployeeRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("employee/raport")
public class EmployeeRaports {

	private final EmployeeRaport raport;

	@GetMapping("summary")
	public ResponseEntity<List<EmployeeQuery.NameJob>> summary() {

		List<EmployeeQuery.NameJob> summary = raport.getEmployeesSummaries();
		return ResponseEntity.ok(summary);
	}

	@GetMapping("summary/{jobTitle}/{officeCode}")
	public List<EmployeeQuery.NameJobOffice> summaryOffice(@PathVariable String jobTitle,
			@PathVariable String officeCode) {

		return raport.getEmployeeOfficeSummaries(jobTitle, officeCode);
	}

	@GetMapping("officeCodeBetween/{low}/{high}")
	public List<EmployeeQuery.NameJobOffice> officeCodeBetween(@PathVariable String low,
			@PathVariable String high) {

		return raport.findEmployeeWhereOfficeCodeBetween(low, high);
	}

	@GetMapping("lastname/{lastName}")
	public List<EmployeeQuery.NameJob> lastNameContaining(@PathVariable String lastName) {
		return raport.findEmployeeByLastNameContaining(lastName);
	}

	@GetMapping("withCustomerPayments")
	public List<EmployeeQuery.WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return raport.getEmployeesWithCustomersPayments();
	}

	@GetMapping("withCustomerNumber")
	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithCustomersNumbers() {

		return raport.getEmployeesWithCustomerNumbers();
	}

	@GetMapping("withoutCustomerNumber")
	public List<EmployeeQuery.WithCustomerNumber> getEmployeesWithoutCustomersNumbers() {

		return raport.getEmployeesWithoutCustomerNumbers();
	}

	@GetMapping("withReportsTo")
	public List<EmployeeQuery.WithManager> getEmployeesWithReportsTo() {

		return raport.getEmployeesWithReportsTo();
	}
}
