package karolh95.classicmodels.controller.report;

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
import karolh95.classicmodels.dto.projection.customer.NameCreditLimit;
import karolh95.classicmodels.dto.projection.customer.NameCreditLimitCountryState;
import karolh95.classicmodels.dto.projection.customer.NameNumber;
import karolh95.classicmodels.dto.projection.customer.NameSalesRepCountry;
import karolh95.classicmodels.dto.projection.customer.WithOrderNameStatus;
import karolh95.classicmodels.service.report.CustomerReport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Customer.REPORT)
public class CustomerReports {

	private final CustomerReport report;

	@GetMapping(Customer.Report.CONTACTS_ASC)
	public List<Contact> contactsAsc() {

		return report.findAllContactsAsc();
	}

	@GetMapping(Customer.Report.CONTACTS_DESC)
	public List<Contact> contactsDesc() {

		return report.findAllContactsDesc();
	}

	@GetMapping(Customer.Report.STATES_ALL)
	public List<String> getDistinctState() {

		return report.findDistinctState();
	}

	@GetMapping(Customer.Report.STATES_FIRST_5)
	public List<String> getFirst5State() {

		return report.findFirst5States();
	}

	@GetMapping(Customer.Report.STATES_CITY)
	public List<StateCity> getDistinctStateCity() {
		return report.findDistinctStateCity();
	}

	@GetMapping(Customer.Report.STATES_BY_COUNTRY)
	public Long getStatesByCountry(@PathVariable String country) {

		return report.countStatesByCountry(country);
	}

	@GetMapping(Customer.Report.BY_COUNTRY_STATE)
	public List<NameCountryState> findByCountryAndState(@PathVariable String country,
			@PathVariable String state) {

		return report.findByCountryAndState(country, state);
	}

	@GetMapping(Customer.Report.BY_COUNTRY_STATE_CREDITLIMIT)
	public List<NameCreditLimitCountryState> findByCountryStateAndCreditlimit(
			@PathVariable String country, @PathVariable String state,
			@PathVariable BigDecimal creditLimit) {

		return report.findByCountryAndStateAndCreditLimitGreaterThan(country, state, creditLimit);
	}

	@GetMapping(Customer.Report.BY_CREDITLIMIT_COUNTRIES)
	public List<NameCreditLimitCountryState> findOr(@PathVariable BigDecimal creditLimit,
			@PathVariable String country1, @PathVariable String country2) {

		return report.findByCountry(creditLimit, country1, country2);
	}

	@GetMapping(Customer.Report.BY_PAGE)
	public List<NameNumber> findByPage(@PathVariable int page, @PathVariable int size) {

		return report.findBy(page, size);
	}

	@GetMapping(Customer.Report.CREDITLIMIT_NTH_LOWEST)
	public List<NameCreditLimit> findNthLowestCreditLimit(@PathVariable int n) {

		return report.findNthLowest(n);
	}

	@GetMapping(Customer.Report.CREDITLIMIT_NTH_HIGHEST)
	public List<NameCreditLimit> findNthHighestCreditLimit(@PathVariable int n) {

		return report.findNthHighest(n);
	}

	@GetMapping(Customer.Report.SALESREP)
	public List<NameSalesRepCountry> getSalesRep() {

		return report.findSalesRep();
	}

	@GetMapping(Customer.Report.WITH_ORDERS)
	public List<WithOrderNameStatus> getCustomersWithOrders() {

		return report.getCustomersWithOrders();
	}

	@GetMapping(Customer.Report.WITHOUT_ORDERS)
	public List<WithOrderNameStatus> getCustomersWithoutOrders() {

		return report.getCustomersWithoutOrders();
	}
}
