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
import karolh95.classicmodels.controller.mapping.Customer;
import karolh95.classicmodels.controller.mapping.Mappings;
import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.service.CustomerService;

@RestController
@RequestMapping(Mappings.CUSTOMER)
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping(Customer.ALL)
	public List<DtoCustomer> getAllCustomers() {

		return this.service.getAllCustomers();
	}

	@GetMapping(Customer.GET)
	public ResponseEntity<DtoCustomer> getCustomer(@PathVariable Long id) {

		DtoCustomer response = service.getCustomer(id);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(Customer.SAVE)
	public ResponseEntity<DtoCustomer> save(@Valid @RequestBody DtoCustomer customer,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(customer);
		}

		DtoCustomer response = service.saveCustomer(customer);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}
