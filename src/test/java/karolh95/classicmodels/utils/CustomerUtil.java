package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Employee;

public final class CustomerUtil {

	private static final String CUSTOMER_NAME = "customerName";
	private static final String CONTACT_LAST_NAME = "contactLastName";
	private static final String CONTACT_FIRST_NAME = "contactFirstName";
	private static final String POSTAL_CODE = "postalCode";
	private static final BigDecimal CREDIT_LIMIT = new BigDecimal("10000.00");

	private static final String NEW_CUSTOMER_NAME = "new_customerName";
	private static final String NEW_CONTACT_LAST_NAME = "new_contactLastName";
	private static final String NEW_CONTACT_FIRST_NAME = "new_contactFirstName";
	private static final String NEW_POSTAL_CODE = "new_postalCode";
	private static final BigDecimal NEW_CREDIT_LIMIT = new BigDecimal("20000.00");

	private static final Long CUSTOMER_NUMBER_MIN = 1L;
	private static final Long NEW_CUSTOMER_NUMBER = 2L;
	private static final Long CUSTOMER_NUMBER_MAX = 6L;

	private CustomerUtil() {
	}

	public static Customer customer() {

		return customer(CUSTOMER_NUMBER_MIN);
	}

	private static Customer customer(Long customerNumber) {

		Customer customer = new Customer();

		customer.setCustomerNumber(customerNumber);
		customer.setCustomerName(CUSTOMER_NAME);
		customer.setContactLastName(CONTACT_LAST_NAME);
		customer.setContactFirstName(CONTACT_FIRST_NAME);
		customer.setPostalCode(POSTAL_CODE);
		customer.setCreditLimit(CREDIT_LIMIT);

		customer.setAddress(AddressUtil.address());

		Employee employee = EmployeeUtil.employee();

		customer.setEmployee(employee);
		customer.setSalesRepEmployeeNumber(employee.getEmployeeNumber());


		return customer;
	}

	public static DtoCustomer dtoNewCustomer() {

		DtoCustomer customer = new DtoCustomer();

		customer.setCustomerNumber(NEW_CUSTOMER_NUMBER);
		customer.setCustomerName(NEW_CUSTOMER_NAME);
		customer.setContactLastName(NEW_CONTACT_LAST_NAME);
		customer.setContactFirstName(NEW_CONTACT_FIRST_NAME);
		customer.setPostalCode(NEW_POSTAL_CODE);
		customer.setCreditLimit(NEW_CREDIT_LIMIT);

		customer.setAddress(AddressUtil.dtoNewAddress());
		customer.setSalesRepEmployeeNumber(EmployeeUtil.dtoNewEmployee().getEmployeeNumber());

		return customer;
	}

	public static List<Customer> customers() {

		List<Customer> customers = new ArrayList<>();

		for (Long customerNumber =
				CUSTOMER_NUMBER_MIN; customerNumber < CUSTOMER_NUMBER_MAX; customerNumber++) {
			customers.add(customer(customerNumber));
		}

		return customers;
	}

	public static void assertEquals(Customer customer, DtoCustomer dtoCustomer) {

		assertNotNull(customer, "Customer should not be null");
		assertNotNull(dtoCustomer, "DTO customer should not be null");

		assertCustomersNumbersEquals(customer.getCustomerNumber(), dtoCustomer.getCustomerNumber());
		assertModifiableFieldsEquals(customer, dtoCustomer);
		assertEmployeesNumbersEquals(customer.getEmployee(),
				dtoCustomer.getSalesRepEmployeeNumber());
	}

	public static void assertUpdated(Customer original, DtoCustomer dtoCustomer,
			Customer customer) {

		assertCustomersNumbersEquals(original.getCustomerNumber(), customer.getCustomerNumber());
		assertModifiableFieldsEquals(customer, dtoCustomer);
	}

	private static void assertCustomersNumbersEquals(Long expected, Long actual) {

		Assertions.assertEquals(expected, actual, "Customer number should match");
	}

	private static void assertEmployeesNumbersEquals(Employee employee, Long employeeNumber) {

		assertNotNull(employee, "Employee should not be null");
		Assertions.assertEquals(employee.getEmployeeNumber(), employeeNumber,
				"Employee number should match");
	}

	private static void assertModifiableFieldsEquals(Customer customer, DtoCustomer dtoCustomer) {

		Assertions.assertEquals(customer.getCustomerName(), dtoCustomer.getCustomerName(),
				"Customer name should match");
		Assertions.assertEquals(customer.getContactLastName(), dtoCustomer.getContactLastName(),
				"Contact last name should match");
		Assertions.assertEquals(customer.getContactFirstName(), dtoCustomer.getContactFirstName(),
				"Contact first name should match");

		Assertions.assertEquals(customer.getPostalCode(), dtoCustomer.getPostalCode(),
				"Postal code should match");
		Assertions.assertEquals(customer.getCreditLimit(), dtoCustomer.getCreditLimit(),
				"Credit limit should match");
	}
}
