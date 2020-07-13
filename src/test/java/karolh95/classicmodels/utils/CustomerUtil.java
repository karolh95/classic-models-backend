package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Employee;

public class CustomerUtil {
	public static Customer customer() {

		Customer customer = new Customer();

		customer.setCustomerNumber(1L);
		customer.setCustomerName("customerName");
		customer.setContactLastName("contactLastName");
		customer.setContactFirstName("contactFirstName");
		customer.setPostalCode("postalCode");
		customer.setCreditLimit(new BigDecimal("10000.00"));

		customer.setAddress(AddressUtil.address());
		customer.setEmployee(EmployeeUtil.employee());

		return customer;
	}

	public static DtoCustomer dtoNewCustomer() {

		DtoCustomer customer = new DtoCustomer();

		customer.setCustomerNumber(2L);
		customer.setCustomerName("new_customerName");
		customer.setContactLastName("new_contactLastName");
		customer.setContactFirstName("new_contactFirstName");
		customer.setPostalCode("new_postalCode");
		customer.setCreditLimit(new BigDecimal("20000.00"));

		customer.setAddress(AddressUtil.dtoNewAddress());

		return customer;
	}

	public static List<Customer> customers() {

		List<Customer> customers = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			customers.add(customer());
		}

		return customers;
	}

	public static void assertEquals(Customer customer, DtoCustomer dtoCustomer) {

		assertNotNull(customer, "Customer should not be null");
		assertNotNull(dtoCustomer, "DTO customer should not be null");

		Assertions.assertEquals(customer.getCustomerNumber(), dtoCustomer.getCustomerNumber(),
				"Customer number should match");
		Assertions.assertEquals(customer.getCustomerName(), dtoCustomer.getCustomerName(),
				"Customer name should match");
		Assertions.assertEquals(customer.getContactLastName(), dtoCustomer.getContactLastName(),
				"Contact last name should match");
		Assertions.assertEquals(customer.getContactFirstName(), dtoCustomer.getContactFirstName(),
				"Contact first name should match");
		Assertions.assertEquals(customer.getPostalCode(), dtoCustomer.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(customer.getCreditLimit(), dtoCustomer.getCreditLimit(), "Credit limit should match");

		AddressUtil.assertEquals(customer.getAddress(), dtoCustomer.getAddress());

		Employee employee = customer.getEmployee();

		assertNotNull(employee, "Employee should not be null");
		Assertions.assertEquals(employee.getEmployeeNumber(), dtoCustomer.getCustomerNumber());
	}

	public static void assertUpdated(Customer original, DtoCustomer dtoCustomer, Customer customer) {

		Assertions.assertEquals(original.getCustomerNumber(), customer.getCustomerNumber(),
				"Customer number should match");
		Assertions.assertEquals(dtoCustomer.getCustomerName(), customer.getCustomerName(),
				"Customer name should match");
		Assertions.assertEquals(dtoCustomer.getContactLastName(), customer.getContactLastName(),
				"Constact last name should match");
		Assertions.assertEquals(dtoCustomer.getContactFirstName(), customer.getContactFirstName(),
				"Contact first name should match");
		Assertions.assertEquals(dtoCustomer.getPostalCode(), customer.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(dtoCustomer.getCreditLimit(), customer.getCreditLimit(), "Credit limit should match");
	}
}