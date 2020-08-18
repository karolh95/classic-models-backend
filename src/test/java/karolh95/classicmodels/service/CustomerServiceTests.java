package karolh95.classicmodels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.mapper.CustomerMapper;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.repository.CustomerRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@Disabled
public class CustomerServiceTests {

	@MockBean
	private CustomerRepository repository;

	@Autowired
	private CustomerMapper mapper;

	@Autowired
	private CustomerService service;

	@Test
	@DisplayName("Should create new Customer")
	public void createCustomer() {

		when(repository.getOne(anyLong())).thenThrow(new EntityNotFoundException());
		when(repository.save(any(Customer.class))).thenReturn(customer());

		DtoCustomer customer = dtoCustomer();
		DtoCustomer response = service.saveCustomer(customer);

		assertEquals(customer, response, "Customer and response should match");
	}

	@Test
	@DisplayName("Should update Customer")
	public void updateCustomer() {

		when(repository.getOne(anyLong())).thenReturn(customer());
		when(repository.save(any(Customer.class))).thenReturn(modifiedCustomer());

		DtoCustomer customer = modifiedDtoCustomer();
		DtoCustomer response = service.saveCustomer(customer);

		assertEquals(customer, response, "Customer and response should match");
	}

	private Customer customer() {

		Customer customer = new Customer();

		customer.setCustomerNumber(1L);
		customer.setCustomerName("customerName");
		customer.setContactLastName("contactLastName");
		customer.setContactFirstName("contactFirstName");
		customer.setAddress(address());
		customer.setPostalCode("postalCode");
		customer.setCreditLimit(new BigDecimal("10000.00"));

		return customer;
	}

	private Address address() {

		Address address = new Address();

		address.setAddressLine1("addressLine1");
		address.setAddressLine2("addressLine2");
		address.setCity("city");
		address.setCountry("country");
		address.setPhone("phone");
		address.setState("state");

		return address;
	}

	private DtoCustomer dtoCustomer() {

		return mapper.customerToDto(customer());
	}

	private Customer modifiedCustomer() {

		Customer customer = customer();

		customer.setCustomerName("newCustomerName");
		customer.setContactLastName("newContactLastName");
		customer.setContactFirstName("newContactFirstName");
		customer.setAddress(modifiedAddress());
		customer.setPostalCode("newPostalCode");
		customer.setCreditLimit(new BigDecimal("15000.00"));

		return customer;
	}

	private Address modifiedAddress() {

		Address address = new Address();

		address.setAddressLine1("newAddressLine1");
		address.setAddressLine2("newAddressLine2");
		address.setCity("newCity");
		address.setCountry("newCountry");
		address.setPhone("newPhone");
		address.setState("newState");

		return address;
	}

	private DtoCustomer modifiedDtoCustomer() {
		return mapper.customerToDto(modifiedCustomer());
	}
}
