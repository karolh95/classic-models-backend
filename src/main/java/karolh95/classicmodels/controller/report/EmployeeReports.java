package karolh95.classicmodels.controller.report;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Employee;
import karolh95.classicmodels.dto.projection.employee.NameJob;
import karolh95.classicmodels.dto.projection.employee.NameJobOffice;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNameAndPayments;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNumber;
import karolh95.classicmodels.dto.projection.employee.WithManager;
import karolh95.classicmodels.service.report.EmployeeReport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Employee.REPORT)
public class EmployeeReports {

	private final EmployeeReport report;

	@GetMapping(Employee.Report.SUMMARY)
	public ResponseEntity<List<NameJob>> summary() {

		List<NameJob> summary = report.getEmployeesSummaries();
		return ResponseEntity.ok(summary);
	}

	@GetMapping(Employee.Report.BY_JOB_OFFICE)
	public List<NameJobOffice> summaryOffice(@PathVariable String jobTitle,
			@PathVariable String officeCode) {

		return report.getEmployeeOfficeSummaries(jobTitle, officeCode);
	}

	@GetMapping(Employee.Report.OFFICECODE_BETWEEN)
	public List<NameJobOffice> officeCodeBetween(@PathVariable String low,
			@PathVariable String high) {

		return report.findEmployeeWhereOfficeCodeBetween(low, high);
	}

	@GetMapping(Employee.Report.LASTNAME)
	public List<NameJob> lastNameContaining(@PathVariable String lastName) {
		return report.findEmployeeByLastNameContaining(lastName);
	}

	@GetMapping(Employee.Report.CUSTOMER_PAYMENTS)
	public List<WithCustomerNameAndPayments> getEmployeesWithCustomersPayments() {

		return report.getEmployeesWithCustomersPayments();
	}

	@GetMapping(Employee.Report.WITH_CUSTOMER)
	public List<WithCustomerNumber> getEmployeesWithCustomersNumbers() {

		return report.getEmployeesWithCustomerNumbers();
	}

	@GetMapping(Employee.Report.WITHOUT_CUSTOMER)
	public List<WithCustomerNumber> getEmployeesWithoutCustomersNumbers() {

		return report.getEmployeesWithoutCustomerNumbers();
	}

	@GetMapping(Employee.Report.WITH_REPORTSTO)
	public List<WithManager> getEmployeesWithReportsTo() {

		return report.getEmployeesWithReportsTo();
	}
}
