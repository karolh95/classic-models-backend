package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.mapper.AddressMapper;
import karolh95.classicmodels.mapper.AddressMapperImpl;
import karolh95.classicmodels.mapper.CustomerMapper;
import karolh95.classicmodels.mapper.CustomerMapperImpl;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Customer;

public class CustomerMapperTests {

	@Spy
	private AddressMapper addressMapper = new AddressMapperImpl();

	@Spy
	private EmployeeResolver resolver;

	@InjectMocks
	private CustomerMapper mapper = new CustomerMapperImpl();

	@Test
	@DisplayName("Should map Customer to Dto")
	public void mapCustomerToDto() {

		MockitoAnnotations.initMocks(this);
		doReturn(null).when(resolver).map(anyLong());
		Customer customer = customer();

		DtoCustomer dtoCustomer = mapper.customerToDto(customer);

		assertCustomersEquals(customer, dtoCustomer);
	}

	protected void assertCustomersEquals(Customer customer, DtoCustomer dtoCustomer) {

		assertEquals(customer.getCustomerNumber(), dtoCustomer.getCustomerNumber(), "Customer number should match");
		assertEquals(customer.getCustomerName(), dtoCustomer.getCustomerName(), "Customer name should match");
		assertEquals(customer.getContactLastName(), dtoCustomer.getContactLastName(), "Contact last name should match");
		assertEquals(customer.getContactFirstName(), dtoCustomer.getContactFirstName(),
				"Contact first name should match");
		assertEquals(customer.getPostalCode(), dtoCustomer.getPostalCode(), "Postal code should match");
		assertEquals(customer.getCreditLimit(), dtoCustomer.getCreditLimit(), "Credit limit should match");

		assertAddressesEqualss(customer.getAddress(), dtoCustomer.getAddress());
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

	private void assertAddressesEqualss(Address address, DtoAddress dtoAddress) {

		assertEquals(address.getPhone(), dtoAddress.getPhone(), "Phone should match");
		assertEquals(address.getAddressLine1(), dtoAddress.getAddressLine1(), "Address line 1 should match");
		assertEquals(address.getAddressLine2(), dtoAddress.getAddressLine2(), "Address line 2 should match");
		assertEquals(address.getCity(), dtoAddress.getCity(), "City should match");
		assertEquals(address.getState(), dtoAddress.getState(), "State should match");
		assertEquals(address.getCountry(), dtoAddress.getCountry(), "Country should match");
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
}