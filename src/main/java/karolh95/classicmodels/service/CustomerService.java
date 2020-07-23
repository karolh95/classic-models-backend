package karolh95.classicmodels.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.dto.query.CustomerContact;
import karolh95.classicmodels.dto.query.CustomerCreditLimit;
import karolh95.classicmodels.dto.query.CustomerDetail;
import karolh95.classicmodels.dto.query.CustomerFullDetail;
import karolh95.classicmodels.dto.query.CustomerState;
import karolh95.classicmodels.dto.query.CustomerStateCity;
import karolh95.classicmodels.dto.query.CustomerSummary;
import karolh95.classicmodels.mapper.CustomerMapper;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerMapper mapper;
	private final CustomerRepository repository;

	public List<DtoCustomer> getAllCustomers() {

		List<Customer> customers = repository.findAll();
		return mapper.customersToDtos(customers);
	}

	public DtoCustomer getCustomer(Long customerNumber) {

		Optional<Customer> optional = repository.findById(customerNumber);

		if (optional.isEmpty()) {
			return null;
		}

		Customer customer = optional.get();
		return mapper.customerToDto(customer);
	}

	public DtoCustomer saveCustomer(DtoCustomer dtoCustomer) {

		Customer customer = getOne(dtoCustomer.getCustomerNumber());

		mapper.updateFromDto(dtoCustomer, customer);

		if (!customer.hasValidIds()) {
			return null;
		}

		customer = repository.save(customer);

		return mapper.customerToDto(customer);
	}

	private Customer getOne(Long customerNumber) {

		Optional<Customer> optional = repository.findById(customerNumber);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			Customer customer = new Customer();

			customer.setCustomerNumber(customerNumber);

			return customer;
		}
	}

	public List<CustomerContact> findAllCustomerContacts(String order) {

		Supplier<List<CustomerContact>> contacts = repository::findAllByOrderByContactLastName;

		if (order.equalsIgnoreCase("DESC")) {
			contacts = repository::findAllByOrderByContactLastNameDesc;
		}

		return contacts.get();
	}

	public List<CustomerContact> findAllCustomerContactsSort(String order) {

		TypedSort<Customer> customer = Sort.sort(Customer.class);

		Sort sortByLastName = customer.by(Customer::getContactLastName).ascending();
		Sort sortByFirstName = customer.by(Customer::getContactFirstName).ascending();

		if (order.equalsIgnoreCase("DESC")) {
			sortByLastName = sortByLastName.descending();
		}

		Sort sort = sortByLastName.and(sortByFirstName);

		return repository.findAllBy(sort);
	}

	public List<CustomerState> findDistinctState() {

		return repository.findDistinctAddress_StateBy();
	}

	public List<CustomerState> findFirst5States() {

		return repository.findFirst5DistinctAddress_StateByAddress_StateNotNull();
	}

	public List<CustomerStateCity> findDistinctStateCity() {

		TypedSort<CustomerStateCity> customerStateCity = Sort.sort(CustomerStateCity.class);

		Sort byState = customerStateCity.by(CustomerStateCity::getAddress_State).ascending();
		Sort byCity = customerStateCity.by(CustomerStateCity::getAddress_City).ascending();

		Sort sort = byState.and(byCity);

		return repository.findDistinctByAddress_StateNotNull(sort);
	}

	public int countStatesByCountry(String country) {

		return repository.findDistinctAddress_StateByAddress_Country(country).size();
	}

	public List<CustomerDetail> findByCountryAndState(String country, String state) {

		return repository.findByAddress_CountryAndAddress_State(country, state);
	}

	public List<CustomerFullDetail> findByCountryAndStateAndCreditLimitGreaterThan(String country, String state,
			BigDecimal creditlimit) {

		return repository.findByAddress_CountryAndAddress_StateAndCreditLimitGreaterThan(country, state, creditlimit);
	}

	public List<CustomerFullDetail> findByCountry(BigDecimal creditLimit, String... countries) {

		return repository.findByCreditLimitGreaterThanAndAddress_CountryIn(creditLimit, countries);
	}

	public List<CustomerSummary> findBy(int page, int size) {

		TypedSort<CustomerSummary> sort = Sort.sort(CustomerSummary.class);

		Sort sortByCustomerName = sort.by(CustomerSummary::getCustomerName).ascending();

		Pageable pageRequest = PageRequest.of(page, size, sortByCustomerName);

		return repository.findAllBy(pageRequest);
	}

	public List<CustomerCreditLimit> findNthLowestCreditLimit(int n) {

		return findNthCreditLimit(n, Sort::ascending);
	}

	public List<CustomerCreditLimit> findNthHighestCreditLimit(int n) {

		return findNthCreditLimit(n, Sort::descending);
	}

	private List<CustomerCreditLimit> findNthCreditLimit(int n, Function<Sort, Sort> order) {

		TypedSort<CustomerCreditLimit> sort = Sort.sort(CustomerCreditLimit.class);

		Sort sortByCreditLimit = sort.by(CustomerCreditLimit::getCreditLimit);

		sortByCreditLimit = order.apply(sortByCreditLimit);

		Pageable pageRequest = PageRequest.of(n - 1, 1, sortByCreditLimit);

		return repository.findBy(pageRequest);
	}
}