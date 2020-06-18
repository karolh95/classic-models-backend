package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoEmployee;
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
}