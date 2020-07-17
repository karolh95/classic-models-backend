package karolh95.classicmodels.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.dto.query.EmployeeOfficeSummary;
import karolh95.classicmodels.dto.query.EmployeeSummary;
import karolh95.classicmodels.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("all")
	public List<DtoEmployee> getAllEmployees() {

		return service.getAllEmployees();
	}

	@GetMapping("detail/{employeeNumber}")
	public ResponseEntity<DtoEmployee> getEmployee(@PathVariable Long employeeNumber) {

		DtoEmployee response = service.getEmployee(employeeNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("save")
	public ResponseEntity<DtoEmployee> save(@Valid @RequestBody DtoEmployee employee, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(employee);
		}

		DtoEmployee response = service.saveEmployee(employee);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("summary")
	public ResponseEntity<List<EmployeeSummary>> summary() {

		List<EmployeeSummary> summary = service.getEmployeesSummaries();
		return ResponseEntity.ok(summary);
	}

	@GetMapping("summary/{jobTitle}/{officeCode}")
	public List<EmployeeOfficeSummary> summaryOffice(@PathVariable String jobTitle, @PathVariable String officeCode) {

		return service.getEmployeeOfficeSummaries(jobTitle, officeCode);
	}
}