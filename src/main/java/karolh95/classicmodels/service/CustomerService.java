package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.dto.query.CustomerContact;
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
}