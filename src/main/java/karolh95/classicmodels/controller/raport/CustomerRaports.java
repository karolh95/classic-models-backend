package karolh95.classicmodels.controller.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Customer;
import karolh95.classicmodels.dto.projection.address.StateCity;
import karolh95.classicmodels.dto.projection.customer.Contact;
import karolh95.classicmodels.dto.projection.customer.NameCountryState;
import karolh95.classicmodels.dto.projection.customer.NameCreditLimitCountryState;
import karolh95.classicmodels.dto.projection.customer.NameNumber;
import karolh95.classicmodels.dto.projection.customer.NameSalesRepCountry;
import karolh95.classicmodels.dto.projection.customer.WithOrderNameStatus;
import karolh95.classicmodels.dto.query.CustomerQuery;
import karolh95.classicmodels.service.raport.CustomerRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Customer.RAPORT)
public class CustomerRaports {

	private final CustomerRaport raport;

	@GetMapping(Customer.Raport.CONTACTS_ASC)
	public List<Contact> contactsAsc() {

		return raport.findAllContactsAsc();
	}

	@GetMapping(Customer.Raport.CONTACTS_DESC)
	public List<Contact> contactsDesc() {

		return raport.findAllContactsDesc();
	}

	@GetMapping(Customer.Raport.STATES_ALL)
	public List<String> getDistinctState() {

		return raport.findDistinctState();
	}

	@GetMapping(Customer.Raport.STATES_FIRST_5)
	public List<String> getFirst5State() {

		return raport.findFirst5States();
	}

	@GetMapping(Customer.Raport.STATES_CITY)
	public List<StateCity> getDistinctStateCity() {
		return raport.findDistinctStateCity();
	}

	@GetMapping(Customer.Raport.STATES_BY_COUNTRY)
	public Long getStatesByCountry(@PathVariable String country) {

		return raport.countStatesByCountry(country);
	}

	@GetMapping(Customer.Raport.BY_COUNTRY_STATE)
	public List<NameCountryState> findByCountryAndState(@PathVariable String country,
			@PathVariable String state) {

		return raport.findByCountryAndState(country, state);
	}

	@GetMapping(Customer.Raport.BY_COUNTRY_STATE_CREDITLIMIT)
	public List<NameCreditLimitCountryState> findByCountryStateAndCreditlimit(
			@PathVariable String country, @PathVariable String state,
			@PathVariable BigDecimal creditLimit) {

		return raport.findByCountryAndStateAndCreditLimitGreaterThan(country, state, creditLimit);
	}

	@GetMapping(Customer.Raport.BY_CREDITLIMIT_COUNTRIES)
	public List<NameCreditLimitCountryState> findOr(@PathVariable BigDecimal creditLimit,
			@PathVariable String country1, @PathVariable String country2) {

		return raport.findByCountry(creditLimit, country1, country2);
	}

	@GetMapping(Customer.Raport.BY_PAGE)
	public List<NameNumber> findByPage(@PathVariable int page, @PathVariable int size) {

		return raport.findBy(page, size);
	}

	@GetMapping(Customer.Raport.CREDITLIMIT_NTH_LOWEST)
	public List<CustomerQuery.NameCreditLimit> findNthLowestCreditLimit(@PathVariable int n) {

		return raport.findNthLowest(n);
	}

	@GetMapping(Customer.Raport.CREDITLIMIT_NTH_HIGHEST)
	public List<CustomerQuery.NameCreditLimit> findNthHighestCreditLimit(@PathVariable int n) {

		return raport.findNthHighest(n);
	}

	@GetMapping(Customer.Raport.SALESREP)
	public List<NameSalesRepCountry> getSalesRep() {

		return raport.findSalesRep();
	}

	@GetMapping(Customer.Raport.WITH_ORDERS)
	public List<WithOrderNameStatus> getCustomersWithOrders() {

		return raport.getCustomersWithOrders();
	}

	@GetMapping(Customer.Raport.WITHOUT_ORDERS)
	public List<WithOrderNameStatus> getCustomersWithoutOrders() {

		return raport.getCustomersWithoutOrders();
	}
}
