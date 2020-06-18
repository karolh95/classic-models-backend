package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping("all")
	public List<DtoCustomer> getAllCustomers() {

		return this.service.getAllCustomers();
	}

	@GetMapping("detail/{customerNumber}")
	public ResponseEntity<DtoCustomer> getCustomer(@PathVariable Long customerNumber) {

		DtoCustomer response = this.service.getCustomer(customerNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

}