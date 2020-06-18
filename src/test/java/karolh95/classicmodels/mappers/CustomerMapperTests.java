package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.mapper.CustomerMapper;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.repository.CustomerRepository;

@SpringBootTest
public class CustomerMapperTests {

	@Autowired
	private CustomerMapper mapper;

	@Autowired
	private CustomerRepository repository;

	@Test
	@DisplayName("Should map Customer to Dto")
	public void mapCustomerToDto() {

		Optional<Customer> optional = this.repository.findById(168L);

		assertTrue(optional.isPresent(), "Customer should exist");

		Customer customer = optional.get();
		Address address = customer.getAddress();
		DtoCustomer dto = this.mapper.customerToDto(customer);
		DtoAddress dtoAddress = dto.getAddress();

		assertEquals(customer.getCustomerNumber(), dto.getCustomerNumber(), "Customer number should match");
		assertEquals(customer.getCustomerName(), dto.getCustomerName(), "Customer name should match");
		assertEquals(customer.getContactLastName(), dto.getContactLastName(), "Contact last name should match");
		assertEquals(customer.getContactFirstName(), dto.getContactFirstName(), "Contact first name should match");

		assertEquals(address.getAddressLine1(), dtoAddress.getAddressLine1(), "Address line 1 should match");
		assertEquals(address.getAddressLine2(), dtoAddress.getAddressLine2(), "Address line 2 should match");
		assertEquals(address.getCity(), dtoAddress.getCity(), "City should match");
		assertEquals(address.getCountry(), dtoAddress.getCountry(), "Country should match");
		assertEquals(address.getPhone(), dtoAddress.getPhone(), "Phone should match");
		assertEquals(address.getState(), dtoAddress.getState(), "State should match");

		assertEquals(customer.getPostalCode(), dto.getPostalCode(), "Postal code should match");
		assertEquals(customer.getCreditLimit(), dto.getCreditLimit(), "Credit limit should match");
		assertEquals(customer.getEmployee().getEmployeeNumber(), dto.getSalesRepEmployeeNumber(), "Sales Rep Employee number should match");
	}
}