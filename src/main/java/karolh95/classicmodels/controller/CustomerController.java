package karolh95.classicmodels.controller;

import java.math.BigDecimal;
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
import karolh95.classicmodels.dto.query.AddressQuery;
import karolh95.classicmodels.dto.query.CustomerQuery;
import karolh95.classicmodels.service.CustomerService;
import karolh95.classicmodels.service.NthCustomerByCreditLimit;

@RestController
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@Autowired
	private NthCustomerByCreditLimit customerByCreditLimit;

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
	public ResponseEntity<List<CustomerQuery.Contact>> contacts(@PathVariable String order) {

		List<CustomerQuery.Contact> contacts = service.findAllCustomerContactsSort(order);

		return ResponseEntity.ok(contacts);
	}

	@GetMapping("state")
	public List<AddressQuery.State> getDistinctState() {

		return service.findDistinctState();
	}

	@GetMapping("state/first/5")
	public List<AddressQuery.State> getFirst5State() {

		return service.findFirst5States();
	}

	@GetMapping("state/city")
	public List<AddressQuery.StateCity> getDistinctStateCity() {
		return service.findDistinctStateCity();
	}

	@GetMapping("{country}/states")
	public int getStatesByCountry(@PathVariable String country) {

		return service.countStatesByCountry(country);
	}

	@GetMapping("find/{country}/{state}")
	public List<CustomerQuery.NameCountryState> findByCountryAndState(@PathVariable String country,
			@PathVariable String state) {

		return service.findByCountryAndState(country, state);
	}

	@GetMapping("find/{country}/{state}/{creditLimit}")
	public List<CustomerQuery.NameCreditLimitCountryState> findByCountryStateAndCreditlimit(
			@PathVariable String country, @PathVariable String state, @PathVariable BigDecimal creditLimit) {

		return service.findByCountryAndStateAndCreditLimitGreaterThan(country, state, creditLimit);
	}

	@GetMapping("creditlimit/{creditLimit}/countries/{country1}/{country2}")
	public List<CustomerQuery.NameCreditLimitCountryState> findOr(@PathVariable BigDecimal creditLimit,
			@PathVariable String country1, @PathVariable String country2) {

		return service.findByCountry(creditLimit, country1, country2);
	}

	@GetMapping("findByPage/{page}/{size}")
	public List<CustomerQuery.NameNumber> findByPage(@PathVariable int page, @PathVariable int size) {

		return service.findBy(page - 1, size);
	}

	@GetMapping("findNth/lowest/{n}")
	public List<CustomerQuery.NameCreditLimit> findNthLowestCreditLimit(@PathVariable int n) {

		return customerByCreditLimit.findNthLowest(n);
	}

	@GetMapping("findNth/highest/{n}")
	public List<CustomerQuery.NameCreditLimit> findNthHighestCreditLimit(@PathVariable int n) {

		return customerByCreditLimit.findNthHighest(n);
	}

	@GetMapping("getSalesRep")
	public List<CustomerQuery.NameSalesRepCountry> getSalesRep() {

		return service.findSalesRep();
	}
}