package karolh95.classicmodels.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.dto.query.AddressQuery;
import karolh95.classicmodels.dto.query.CustomerQuery;
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

	public List<CustomerQuery.Contact> findAllContactsAsc() {

		return findAllContacts(Sort::ascending);
	}

	public List<CustomerQuery.Contact> findAllContactsDesc() {

		return findAllContacts(Sort::descending);
	}

	public List<AddressQuery.State> findDistinctState() {

		return repository.findDistinctAddress_StateBy();
	}

	public List<AddressQuery.State> findFirst5States() {

		return repository.findFirst5DistinctAddress_StateByAddress_StateNotNull();
	}

	public List<AddressQuery.StateCity> findDistinctStateCity() {

		TypedSort<AddressQuery.StateCity> customerStateCity =
				Sort.sort(AddressQuery.StateCity.class);

		Sort byState = customerStateCity.by(AddressQuery.StateCity::getAddress_State).ascending();
		Sort byCity = customerStateCity.by(AddressQuery.StateCity::getAddress_City).ascending();

		Sort sort = byState.and(byCity);

		return repository.findDistinctByAddress_StateNotNull(sort);
	}

	public int countStatesByCountry(String country) {

		return repository.findDistinctAddress_StateByAddress_Country(country).size();
	}

	public List<CustomerQuery.NameCountryState> findByCountryAndState(String country,
			String state) {

		return repository.findByAddress_CountryAndAddress_State(country, state);
	}

	public List<CustomerQuery.NameCreditLimitCountryState> findByCountryAndStateAndCreditLimitGreaterThan(
			String country, String state, BigDecimal creditlimit) {

		return repository.findByAddress_CountryAndAddress_StateAndCreditLimitGreaterThan(country,
				state, creditlimit);
	}

	public List<CustomerQuery.NameCreditLimitCountryState> findByCountry(BigDecimal creditLimit,
			String... countries) {

		return repository.findByCreditLimitGreaterThanAndAddress_CountryIn(creditLimit, countries);
	}

	public List<CustomerQuery.NameNumber> findBy(int page, int size) {

		TypedSort<CustomerQuery.NameNumber> sort = Sort.sort(CustomerQuery.NameNumber.class);

		Sort sortByCustomerName = sort.by(CustomerQuery.NameNumber::getCustomerName).ascending();

		Pageable pageRequest = PageRequest.of(page, size, sortByCustomerName);

		return repository.findAllBy(pageRequest);
	}

	public List<CustomerQuery.NameSalesRepCountry> findSalesRep() {

		TypedSort<CustomerQuery.NameSalesRepCountry> salesRep =
				Sort.sort(CustomerQuery.NameSalesRepCountry.class);

		Sort sortByName =
				salesRep.by(CustomerQuery.NameSalesRepCountry::getCustomerName).ascending();

		return repository.findBySalesRepEmployeeNumberIsNotNull(sortByName);
	}

	public List<CustomerQuery.WithOrderNameStatus> getCustomersWithOrders() {

		return repository.findAllByOrders_OrderNumberIsNotNull();
	}

	public List<CustomerQuery.WithOrderNameStatus> getCustomersWithoutOrders() {

		return repository.findAllByOrders_OrderNumberIsNull();
	}

	private List<CustomerQuery.Contact> findAllContacts(Function<Sort, Sort> order) {

		TypedSort<CustomerQuery.Contact> contact = Sort.sort(CustomerQuery.Contact.class);

		Sort byLastName = contact.by(CustomerQuery.Contact::getContactLastName);
		Sort byFirstName = contact.by(CustomerQuery.Contact::getContactFirstName);

		Sort sortByLastName = order.apply(byLastName);
		Sort sortByFirstName = order.apply(byFirstName);

		return repository.findAllBy(sortByLastName.and(sortByFirstName));
	}
}
