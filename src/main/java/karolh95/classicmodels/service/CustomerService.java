package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoCustomer;
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

		if (dtoCustomer == null || !dtoCustomer.isValid()) {
			return null;
		}

		Customer customer = getOne(dtoCustomer.getCustomerNumber());

		mapper.updateFromDto(dtoCustomer, customer);

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
}