package karolh95.classicmodels.mapper.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.repository.CustomerRepository;

@Component
public class CustomerResolver {

	@Autowired
	private CustomerRepository repository;

	public Customer map(Long customerNumber) {

		if (customerNumber == null) {
			return null;
		}

		Optional<Customer> optional = repository.findById(customerNumber);

		if (optional.isPresent()){

			return optional.get();
			
		} else {

			return null;
		}
	}
}