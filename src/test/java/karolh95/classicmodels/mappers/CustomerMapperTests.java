package karolh95.classicmodels.mappers;

import static karolh95.classicmodels.utils.CustomerUtil.assertEquals;
import static karolh95.classicmodels.utils.CustomerUtil.assertUpdated;
import static karolh95.classicmodels.utils.CustomerUtil.customer;
import static karolh95.classicmodels.utils.CustomerUtil.customers;
import static karolh95.classicmodels.utils.CustomerUtil.dtoNewCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.mapper.AddressMapper;
import karolh95.classicmodels.mapper.AddressMapperImpl;
import karolh95.classicmodels.mapper.CustomerMapper;
import karolh95.classicmodels.mapper.CustomerMapperImpl;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.utils.AddressUtil;

public class CustomerMapperTests {

	@Spy
	private AddressMapper addressMapper = new AddressMapperImpl();

	@Mock
	private EmployeeResolver resolver;

	@InjectMocks
	private CustomerMapper mapper = new CustomerMapperImpl();

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		when(addressMapper.addressToDto(any(Address.class))).thenReturn(AddressUtil.dtoAddress());
	}

	@Nested
	class CustomerToDtoTests {

		@Test
		@DisplayName("Should not map null customer")
		void mapNullCustomer() {

			Customer customer = null;

			DtoCustomer dtoCustomer = mapper.customerToDto(customer);

			assertNull(dtoCustomer, "Dto customer should be null");
		}

		@Test
		@DisplayName("Should map Customer to Dto")
		public void mapCustomerToDto() {

			Customer customer = customer();

			DtoCustomer dtoCustomer = mapper.customerToDto(customer);

			assertEquals(customer, dtoCustomer);
		}

	}

	@Nested
	class CustomersToDtosTests {

		List<Customer> customers;
		List<DtoCustomer> dtoCustomers;

		@BeforeEach
		void init() {

			customers = customers();
		}

		@Test
		@DisplayName("Should not map null customers")
		void mapNullCustomersToDtos() {

			customers = null;

			dtoCustomers = mapper.customersToDtos(customers);

			assertNull(dtoCustomers, "Customers should be null");
		}

		@Test
		@DisplayName("Should map empty customers to empty dtos")
		void mapEmptyCustomersToDtos() {

			customers.clear();

			dtoCustomers = mapper.customersToDtos(customers);

			assertTrue(dtoCustomers.isEmpty(), "Customers should not contain elements");
		}

		@Test
		@DisplayName("Should map customers to dtos")
		void mapCustomersToDtos() {

			dtoCustomers = mapper.customersToDtos(customers);

			for (int i = 0; i < customers.size(); i++) {

				Customer customer = customers.get(i);
				DtoCustomer dtoCustomer = dtoCustomers.get(i);

				assertEquals(customer, dtoCustomer);
			}
		}
	}

	@Nested
	class UpdateFromDtoTests {

		final Customer original = customer();
		Customer customer;
		DtoCustomer dtoCustomer;

		@BeforeEach
		void init() {

			MockitoAnnotations.initMocks(CustomerMapperTests.this);
			when(addressMapper.addressToDto(any(Address.class))).thenReturn(AddressUtil.dtoNewAddress());

			customer = customer();
			dtoCustomer = dtoNewCustomer();
		}

		@Test
		@DisplayName("Should not update Customer from null dto")
		void updateCustomerFromNullDto() {

			dtoCustomer = null;

			mapper.updateFromDto(dtoCustomer, customer);

			assertEquals(original, customer);
		}

		@Test
		@DisplayName("Should update Customer without address from dto")
		void updateCustomerWithoutAddressFromDto() {

			customer.setAddress(null);

			mapper.updateFromDto(dtoCustomer, customer);

			assertUpdated(original, dtoCustomer, customer);
			AddressUtil.assertEquals(customer.getAddress(), dtoCustomer.getAddress());
		}

		@Test
		@DisplayName("Should update Customer from dto without address")
		void updateCustomerFromDtoWithoutAddress() {

			dtoCustomer.setAddress(null);

			mapper.updateFromDto(dtoCustomer, customer);

			assertUpdated(original, dtoCustomer, customer);
			assertNull(customer.getAddress(), "Address should be null");
		}

		@Test
		@DisplayName("Should update Customer from dto")
		void updateCustomerFromDto() {

			mapper.updateFromDto(dtoCustomer, customer);

			assertUpdated(original, dtoCustomer, customer);
			AddressUtil.assertEquals(customer.getAddress(), dtoCustomer.getAddress());
		}

	}
}