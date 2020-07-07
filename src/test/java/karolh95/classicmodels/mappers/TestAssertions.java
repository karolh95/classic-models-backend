package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.dto.DtoOrder;
import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Productline;

public class TestAssertions {

	public static void assertEquals(Productline productline, DtoProductline dtoProductline) {

		assertNotNull(productline, "Productline should not be null");
		assertNotNull(dtoProductline, "DTO Productline should not be null");

		Assertions.assertEquals(productline.getProductline(), dtoProductline.getProductline(),
				"Productline should match");
		Assertions.assertEquals(productline.getTextDescription(), dtoProductline.getTextDescription(),
				"Text description should match");
		Assertions.assertEquals(productline.getHtmlDescription(), dtoProductline.getHtmlDescription(),
				"Html description should match");
		assertArrayEquals(productline.getImage(), dtoProductline.getImage(), "Image should match");
	}

	public static void assertEquals(Product product, DtoProduct dtoProduct) {

		assertNotNull(product, "Product should not be null");
		assertNotNull(dtoProduct, "DTO Product should not be null");

		Assertions.assertEquals(product.getProductCode(), dtoProduct.getProductCode(), "Product code should match");
		Assertions.assertEquals(product.getProductName(), dtoProduct.getProductName(), "Product name should match");
		Assertions.assertEquals(product.getProductScale(), dtoProduct.getProductScale(), "Product scale should match");
		Assertions.assertEquals(product.getProductVendor(), dtoProduct.getProductVendor(),
				"Product vendor should match");
		Assertions.assertEquals(product.getProductDescription(), dtoProduct.getProductDescription(),
				"Product description should match");
		Assertions.assertEquals(product.getQuantityInStock(), dtoProduct.getQuantityInStock(),
				"Quantity in stock should match");
		Assertions.assertEquals(product.getMSRP(), dtoProduct.getMSRP(), "MSRP should match");

		Productline productline = product.getProductline();

		assertNotNull(productline, "Productline shoud not be null");
		Assertions.assertEquals(productline.getProductline(), dtoProduct.getProductline(), "Productline should match");
	}

	public static void assertEquals(Address address, DtoAddress dtoAddress) {

		assertNotNull(address, "Address should not be null");
		assertNotNull(dtoAddress, "DTO address should not be null");

		Assertions.assertEquals(address.getAddressLine1(), dtoAddress.getAddressLine1(), "Address line 1 should match");
		Assertions.assertEquals(address.getAddressLine2(), dtoAddress.getAddressLine2(), "Address line 2 should match");
		Assertions.assertEquals(address.getCity(), dtoAddress.getCity(), "City should match");
		Assertions.assertEquals(address.getCountry(), dtoAddress.getCountry(), "Country should match");
		Assertions.assertEquals(address.getState(), dtoAddress.getState(), "State should match");
		Assertions.assertEquals(address.getPhone(), dtoAddress.getPhone(), "Phone should match");
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

		assertEquals(customer.getAddress(), dtoCustomer.getAddress());

		Employee employee = customer.getEmployee();

		assertNotNull(employee, "Employee should not be null");
		Assertions.assertEquals(employee.getEmployeeNumber(), dtoCustomer.getCustomerNumber());
	}

	public static void assertEquals(Employee employee, DtoEmployee dtoEmployee) {

		assertNotNull(employee, "Employee should not be null");
		assertNotNull(dtoEmployee, "DTO employee should not be null");

		Assertions.assertEquals(employee.getEmployeeNumber(), dtoEmployee.getEmployeeNumber(),
				"Employee number should match");
		Assertions.assertEquals(employee.getLastName(), dtoEmployee.getLastName(), "Last name should match");
		Assertions.assertEquals(employee.getFirstName(), dtoEmployee.getFirstName(), "First name should match");
		Assertions.assertEquals(employee.getExtension(), dtoEmployee.getExtension(), "Extension shouold match");
		Assertions.assertEquals(employee.getEmail(), dtoEmployee.getEmail(), "Email should match");
		Assertions.assertEquals(employee.getJobTitle(), dtoEmployee.getJobTitle(), "Job title should match");

		Office office = employee.getOffice();

		assertNotNull(office, "Office should not be null");
		Assertions.assertEquals(office.getOfficeCode(), dtoEmployee.getOfficeCode());
	}

	public static void assertEquals(Office office, DtoOffice dtoOffice) {

		assertNotNull(office, "Office should not be null");
		assertNotNull(dtoOffice, "DTO office should not be null");

		Assertions.assertEquals(office.getOfficeCode(), dtoOffice.getOfficeCode(), "Office code should match");
		Assertions.assertEquals(office.getPostalCode(), dtoOffice.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(office.getTerritory(), dtoOffice.getTerritory(), "Territory should match");

		assertEquals(office.getAddress(), dtoOffice.getAddress());
	}

	public static void assertEquals(Order order, DtoOrder dtoOrder) {

		assertNotNull(order, "Order should not be null");
		assertNotNull(dtoOrder, "DTO order should not be null");

		Assertions.assertEquals(order.getOrderNumber(), dtoOrder.getOrderNumber(), "Order number should match");
		Assertions.assertEquals(order.getOrderDate(), dtoOrder.getOrderDate(), "Order date should match");
		Assertions.assertEquals(order.getRequiredDate(), dtoOrder.getRequiredDate(), "Required date should match");
		Assertions.assertEquals(order.getShippedDate(), dtoOrder.getShippedDate(), "Shipped date should match");
		Assertions.assertEquals(order.getStatus(), dtoOrder.getStatus(), "Status should match");
		Assertions.assertEquals(order.getComments(), dtoOrder.getComments(), "Comments should match");

		Customer customer = order.getCustomer();

		assertNotNull(customer, "Customer should not be nnull");
		Assertions.assertEquals(customer.getCustomerNumber(), dtoOrder.getCustomerNumber(),
				"Customer number should match");
	}

	public static void assertEquals(OrderDetail orderDetail, DtoOrderDetail dtoOrderDetail) {

		assertNotNull(orderDetail, "Order detail should not be null");
		assertNotNull(dtoOrderDetail, "DTO order detail should not be null");

		Assertions.assertEquals(orderDetail.getQuantityOrdered(), dtoOrderDetail.getQuantityOrdered(),
				"Quantity ordered should match");
		Assertions.assertEquals(orderDetail.getPriceEach(), dtoOrderDetail.getPriceEach(), "Price each should match");
		Assertions.assertEquals(orderDetail.getOrderLineNumber(), dtoOrderDetail.getOrderLineNumber(),
				"Order line number should match");

		OrderDetailPK pk = orderDetail.getOrderDetailPK();

		assertNotNull(pk, "Order detail PK should not be null");
		Assertions.assertEquals(pk.getOrderNumber(), dtoOrderDetail.getOrderNumber(), "Order number should match");
		Assertions.assertEquals(pk.getProductCode(), dtoOrderDetail.getProductCode(), "Product code should match");

		Order order = orderDetail.getOrder();

		assertNotNull(order, "Order should not be null");
		Assertions.assertEquals(order.getOrderNumber(), dtoOrderDetail.getOrderNumber());

		Product product = orderDetail.getProduct();

		assertNotNull(product, "Product should not be null");
		Assertions.assertEquals(product.getProductCode(), dtoOrderDetail.getProductCode());

	}

	public static void assertEquals(Payment payment, DtoPayment dtoPayment) {

		assertNotNull(payment, "Payment should not be null");
		assertNotNull(dtoPayment, "DTO payment should not be null");

		Assertions.assertEquals(payment.getAmount(), dtoPayment.getAmount(), "Amount should match");
		Assertions.assertEquals(payment.getPaymentDate(), dtoPayment.getPaymentDate(), "Date should match");

		Customer customer = payment.getCustomer();

		assertNotNull(customer, "Customer should not be null");
		Assertions.assertEquals(customer.getCustomerNumber(), dtoPayment.getCustomerNumber(),
				"Customer number should match");

		PaymentPK pk = payment.getPaymentPK();

		assertNotNull(pk, "Payment PK should not be null");
		Assertions.assertEquals(pk.getCheckNumber(), dtoPayment.getCheckNumber(), "Check number should match");
		Assertions.assertEquals(pk.getCustomerNumber(), dtoPayment.getCustomerNumber());
	}
}