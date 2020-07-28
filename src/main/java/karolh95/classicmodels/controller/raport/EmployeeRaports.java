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
	public ResponseEntity<List<EmployeeQuery.NameJobTitle>> summary() {

		List<EmployeeQuery.NameJobTitle> summary = service.getEmployeesSummaries();
		return ResponseEntity.ok(summary);
	}

	@GetMapping("summary/{jobTitle}/{officeCode}")
	public List<EmployeeQuery.NameJobTitleOffice> summaryOffice(@PathVariable String jobTitle,
			@PathVariable String officeCode) {

		return service.getEmployeeOfficeSummaries(jobTitle, officeCode);
	}

	@GetMapping("officeCodeBetween/{low}/{high}")
	public List<EmployeeQuery.NameJobTitleOffice> officeCodeBetween(@PathVariable String low,
			@PathVariable String high) {

		return service.findEmployeeWhereOfficeCodeBetween(low, high);
	}

	@GetMapping("lastname/{lastName}")
	public List<EmployeeQuery.NameJobTitle> lastNameContaining(@PathVariable String lastName) {
		return service.findEmployeeByLastNameContaining(lastName);
	}
}
