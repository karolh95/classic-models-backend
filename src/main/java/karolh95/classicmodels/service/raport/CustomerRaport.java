package karolh95.classicmodels.service.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.criteria.util.CriteriaUtil;
import karolh95.classicmodels.dto.projection.address.StateCity;
import karolh95.classicmodels.dto.projection.customer.Contact;
import karolh95.classicmodels.dto.projection.customer.NameCountryState;
import karolh95.classicmodels.dto.projection.customer.NameCreditLimitCountryState;
import karolh95.classicmodels.dto.projection.customer.NameNumber;
import karolh95.classicmodels.dto.projection.customer.NameSalesRepCountry;
import karolh95.classicmodels.dto.projection.customer.WithOrderNameStatus;
import karolh95.classicmodels.dto.query.CustomerQuery;
import karolh95.classicmodels.repository.CustomerRepository;
import karolh95.classicmodels.service.NthCustomerByCreditLimit;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerRaport {

	private final CustomerRepository repository;
	private final NthCustomerByCreditLimit customerByCreditLimit;

	public List<Contact> findAllContactsAsc() {

		return repository.findAllContacts(CriteriaUtil::asc);
	}

	public List<Contact> findAllContactsDesc() {

		return repository.findAllContacts(CriteriaUtil::desc);
	}

	public List<String> findDistinctState() {

		return repository.findDistinctStates();
	}

	public List<String> findFirst5States() {

		return repository.findFirst5DistinctStates();
	}

	public List<StateCity> findDistinctStateCity() {

		return repository.findDistinctStateCity();
	}

	public Long countStatesByCountry(String country) {

		return repository.countStatesByCountry(country);
	}

	public List<NameCountryState> findByCountryAndState(String country, String state) {

		return repository.findByCountryState(country, state);
	}

	public List<NameCreditLimitCountryState> findByCountryAndStateAndCreditLimitGreaterThan(
			String country, String state, BigDecimal creditLimit) {

		return repository.findByCountryStateMinCreditLimit(creditLimit, country, state);
	}

	public List<NameCreditLimitCountryState> findByCountry(BigDecimal creditLimit,
			String... countries) {

		return repository.findByMinCreditLimitCountries(creditLimit, countries);
	}

	public List<NameNumber> findBy(int page, int size) {

		return repository.find(page, size);
	}

	public List<NameSalesRepCountry> findSalesRep() {

		return repository.findSalesRepCountry();
	}

	public List<WithOrderNameStatus> getCustomersWithOrders() {

		return repository.findByOrderNumberNotNull();
	}

	public List<WithOrderNameStatus> getCustomersWithoutOrders() {

		return repository.findByOrderNumberNull();
	}

	public List<CustomerQuery.NameCreditLimit> findNthLowest(int n) {
		return customerByCreditLimit.findNthLowest(n);
	}

	public List<CustomerQuery.NameCreditLimit> findNthHighest(int n) {
		return customerByCreditLimit.findNthHighest(n);
	}
}
