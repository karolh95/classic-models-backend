package karolh95.classicmodels;

import java.math.BigDecimal;
import java.sql.Date;

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

public class TestObject {

	private static final Long NOW = System.currentTimeMillis();

	private static final Long SECOND = 1000L;
	private static final Long MINUTE = 60L * SECOND;
	private static final Long HOUR = 60L * MINUTE;
	private static final Long DAY = 24L * HOUR;
	private static final Long WEEK = 7L * DAY;

	public static Productline productline() {

		Productline productline = new Productline();

		productline.setProductline("productline");
		productline.setTextDescription("textDescription");
		productline.setHtmlDescription("htmlDescription");
		productline.setImage(new byte[1]);

		return productline;
	}

	public static Product product() {

		Product product = new Product();

		product.setProductCode("productCode");
		product.setProductName("productName");
		product.setProductScale("productScale");
		product.setProductVendor("productVendor");
		product.setProductDescription("productDescription");
		product.setQuantityInStock((short) 0);
		product.setBuyPrice(new BigDecimal("1.00"));
		product.setMSRP(new BigDecimal("0.25"));

		product.setProductline(productline());

		return product;
	}

	public static Address address() {

		Address address = new Address();

		address.setAddressLine1("addressLine1");
		address.setAddressLine2("addressLine2");
		address.setCity("city");
		address.setCountry("country");
		address.setPhone("phone");
		address.setState("state");

		return address;
	}

	public static Customer customer() {

		Customer customer = new Customer();

		customer.setCustomerNumber(1L);
		customer.setCustomerName("customerName");
		customer.setContactLastName("contactLastName");
		customer.setContactFirstName("contactFirstName");
		customer.setPostalCode("postalCode");
		customer.setCreditLimit(new BigDecimal("10000.00"));

		customer.setAddress(address());
		customer.setEmployee(employee());

		return customer;
	}

	public static Employee employee() {

		Employee employee = new Employee();

		employee.setEmployeeNumber(1L);
		employee.setLastName("lastName");
		employee.setFirstName("firstName");
		employee.setExtension("extension");
		employee.setEmail("email");
		employee.setJobTitle("jobTitle");

		employee.setOffice(office());

		return employee;
	}

	public static Office office() {

		Office office = new Office();

		office.setOfficeCode("1");
		office.setPostalCode("postalCode");
		office.setTerritory("territory");

		office.setAddress(address());

		return office;
	}

	public static Order order() {

		Order order = new Order();

		order.setOrderNumber(1L);
		order.setOrderDate(new Date(NOW));
		order.setRequiredDate(new Date(NOW + WEEK));
		order.setShippedDate(new Date(NOW + 3 * DAY));
		order.setStatus("status");
		order.setComments("comments");

		order.setCustomer(customer());

		return order;
	}

	public static OrderDetail orderDetail() {

		OrderDetail orderDetail = new OrderDetail();

		Order order = order();
		Product product = product();
		OrderDetailPK pk = new OrderDetailPK(order.getOrderNumber(), product.getProductCode());

		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantityOrdered(1024);
		orderDetail.setOrderDetailPK(pk);
		orderDetail.setPriceEach(new BigDecimal(10.0));
		orderDetail.setOrderLineNumber((short) 1);

		return orderDetail;
	}

	public static Payment payment() {

		Payment payment = new Payment();

		String checkNumber = "checkNumber";
		Customer customer = customer();

		PaymentPK pk = new PaymentPK(customer.getCustomerNumber(), checkNumber);

		payment.setPaymentPK(pk);
		payment.setAmount(new BigDecimal("25.99"));
		payment.setPaymentDate(new Date(NOW));

		payment.setCustomer(customer);

		return payment;
	}
}