package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoOrder;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Order;

public class OrderUtil {

	private static final Long NOW = System.currentTimeMillis();

	private static final Long SECOND = 1000L;
	private static final Long MINUTE = 60L * SECOND;
	private static final Long HOUR = 60L * MINUTE;
	private static final Long DAY = 24L * HOUR;
	private static final Long WEEK = 7L * DAY;

	public static Order order() {

		Order order = new Order();

		order.setOrderNumber(1L);
		order.setOrderDate(new Date(NOW));
		order.setRequiredDate(new Date(NOW + WEEK));
		order.setShippedDate(new Date(NOW + 3 * DAY));
		order.setStatus("status");
		order.setComments("comments");

		order.setCustomer(CustomerUtil.customer());

		return order;
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
}