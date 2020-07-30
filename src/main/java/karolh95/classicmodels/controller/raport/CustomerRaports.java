package karolh95.classicmodels.controller.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.AddressQuery;
import karolh95.classicmodels.dto.query.CustomerQuery;
import karolh95.classicmodels.service.raport.CustomerRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("customer/raport")
public class CustomerRaports {

	private final CustomerRaport raport;

	@GetMapping("contacts/asc")
	public List<CustomerQuery.Contact> contactsAsc() {

		return raport.findAllContactsAsc();
	}

	@GetMapping("contacts/desc")
	public List<CustomerQuery.Contact> contactsDesc() {

		return raport.findAllContactsDesc();
	}

	@GetMapping("state")
	public List<AddressQuery.State> getDistinctState() {

		return raport.findDistinctState();
	}

	@GetMapping("state/first/5")
	public List<AddressQuery.State> getFirst5State() {

		return raport.findFirst5States();
	}

	@GetMapping("state/city")
	public List<AddressQuery.StateCity> getDistinctStateCity() {
		return raport.findDistinctStateCity();
	}

	@GetMapping("{country}/states")
	public int getStatesByCountry(@PathVariable String country) {

		return raport.countStatesByCountry(country);
	}

	@GetMapping("find/{country}/{state}")
	public List<CustomerQuery.NameCountryState> findByCountryAndState(@PathVariable String country,
			@PathVariable String state) {

		return raport.findByCountryAndState(country, state);
	}

	@GetMapping("find/{country}/{state}/{creditLimit}")
	public List<CustomerQuery.NameCreditLimitCountryState> findByCountryStateAndCreditlimit(
			@PathVariable String country, @PathVariable String state,
			@PathVariable BigDecimal creditLimit) {

		return raport.findByCountryAndStateAndCreditLimitGreaterThan(country, state, creditLimit);
	}

	@GetMapping("creditlimit/{creditLimit}/countries/{country1}/{country2}")
	public List<CustomerQuery.NameCreditLimitCountryState> findOr(
			@PathVariable BigDecimal creditLimit, @PathVariable String country1,
			@PathVariable String country2) {

		return raport.findByCountry(creditLimit, country1, country2);
	}

	@GetMapping("findByPage/{page}/{size}")
	public List<CustomerQuery.NameNumber> findByPage(@PathVariable int page,
			@PathVariable int size) {

		return raport.findBy(page - 1, size);
	}

	@GetMapping("findNth/lowest/{n}")
	public List<CustomerQuery.NameCreditLimit> findNthLowestCreditLimit(@PathVariable int n) {

		return raport.findNthLowest(n);
	}

	@GetMapping("findNth/highest/{n}")
	public List<CustomerQuery.NameCreditLimit> findNthHighestCreditLimit(@PathVariable int n) {

		return raport.findNthHighest(n);
	}

	@GetMapping("getSalesRep")
	public List<CustomerQuery.NameSalesRepCountry> getSalesRep() {

		return raport.findSalesRep();
	}

	@GetMapping("withOrders")
	public List<CustomerQuery.WithOrderNameStatus> getCustomersWithOrders() {

		return raport.getCustomersWithOrders();
	}

	@GetMapping("withoutOrders")
	public List<CustomerQuery.WithOrderNameStatus> getCustomersWithoutOrders() {

		return raport.getCustomersWithoutOrders();
	}
}
