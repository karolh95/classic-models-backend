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

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.dto.query.CustomerContact;
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

		DtoCustomer response = service.getCustomer(customerNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("save")
	public ResponseEntity<DtoCustomer> save(@Valid @RequestBody DtoCustomer customer, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(customer);
		}

		DtoCustomer response = service.saveCustomer(customer);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("contacts/{order}")
	public ResponseEntity<List<CustomerContact>> contacts(@PathVariable String order) {

		List<CustomerContact> contacts = service.findAllCustomerContacts(order);

		return ResponseEntity.ok(contacts);
	}
}