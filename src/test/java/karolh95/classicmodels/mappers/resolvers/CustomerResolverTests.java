package karolh95.classicmodels.mappers.resolvers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.repository.CustomerRepository;
import karolh95.classicmodels.utils.CustomerUtil;

class CustomerResolverTests {

	@Mock
	CustomerRepository repository;

	@InjectMocks
	CustomerResolver resolver;

	Long customerNumber;
	Customer customer;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		customerNumber = 1L;
	}

	@Test
	@DisplayName("Should not map null customerNumber")
	void mapNullCustomerNumber() {

		customerNumber = null;

		customer = resolver.map(customerNumber);

		assertNull(customer, "Customer should be null");
	}

	@Test
	@DisplayName("Should not map customerNumber when Customer not exists")
	void mapCustomerNumberToNonExistingCustomer() {

		when(repository.findById(anyLong())).thenReturn(empty());

		customer = resolver.map(customerNumber);

		assertNull(customer, "Customer should be null");
	}

	@Test
	@DisplayName("Should map customerNumber when Customer exists")
	void mapProductCodeToExistingCustomer() {

		when(repository.findById(anyLong())).thenReturn(optional());

		customer = resolver.map(customerNumber);

		assertNotNull(customer, "Customer should not be null");
	}

	private Optional<Customer> empty() {
		return Optional.empty();
	}

	private Optional<Customer> optional() {
		return Optional.of(CustomerUtil.customer());
	}

}