package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Order;

public final class OrderUtil {

	private static final Long NOW = System.currentTimeMillis();

	private static final Long SECOND = 1000L;
	private static final Long MINUTE = 60L * SECOND;
	private static final Long HOUR = 60L * MINUTE;
	private static final Long DAY = 24L * HOUR;
	private static final Long WEEK = 7L * DAY;

	private static final Long ORDER_NUMBER_MIN = 1L;
	private static final Long ORDER_NUMBER_MAX = 6L;
	private static final String STATUS = "status";
	private static final String COMMENTS = "comments";
	private static final Date ORDER_DATE = new Date(NOW);
	private static final Date SHIPPED_DATE = new Date(NOW + 3 * DAY);
	private static final Date REQUIRED_DATE = new Date(NOW + WEEK);

	private OrderUtil() {

	}

	public static Order order() {
		return order(ORDER_NUMBER_MIN);
	}

	private static Order order(Long orderNumber) {

		Order order = new Order();

		order.setOrderNumber(orderNumber);
		order.setOrderDate(ORDER_DATE);
		order.setRequiredDate(REQUIRED_DATE);
		order.setShippedDate(SHIPPED_DATE);
		order.setStatus(STATUS);
		order.setComments(COMMENTS);

		order.setCustomer(customer());

		return order;
	}

	private static Customer customer() {

		Customer customer = new Customer();

		customer.setCustomerNumber(CustomerUtil.customer().getCustomerNumber());

		return customer;
	}

	public static List<Order> orders() {

		List<Order> orders = new ArrayList<>();

		for (Long orderNumber = ORDER_NUMBER_MIN; orderNumber < ORDER_NUMBER_MAX; orderNumber++) {
			orders.add(order(orderNumber));
		}

		return orders;
	}

	public static void assertEquals(Order order, DtoSimpleOrder dtoOrder) {

		assertNotNull(order, "Order should not be null");
		assertNotNull(dtoOrder, "DTO order should not be null");

		assertOrdersNumbersEquals(order.getOrderNumber(), dtoOrder.getOrderNumber());
		assertModifiableFieldsEquals(order, dtoOrder);
		assertCustomersNumbersEquals(order.getCustomer(), dtoOrder.getCustomerNumber());
	}

	private static void assertOrdersNumbersEquals(Long expected, Long actual) {

		Assertions.assertEquals(expected, actual, "Order number should match");
	}

	private static void assertModifiableFieldsEquals(Order order, DtoSimpleOrder dtoOrder) {

		Assertions.assertEquals(order.getOrderDate(), dtoOrder.getOrderDate(), "Order date should match");
		Assertions.assertEquals(order.getRequiredDate(), dtoOrder.getRequiredDate(), "Required date should match");
		Assertions.assertEquals(order.getShippedDate(), dtoOrder.getShippedDate(), "Shipped date should match");
		Assertions.assertEquals(order.getStatus(), dtoOrder.getStatus(), "Status should match");
		Assertions.assertEquals(order.getComments(), dtoOrder.getComments(), "Comments should match");
	}

	private static void assertCustomersNumbersEquals(Customer customer, Long customerNumber) {

		assertNotNull(customer, "Customer should not be nnull");
		Assertions.assertEquals(customer.getCustomerNumber(), customerNumber, "Customer number should match");
	}
}