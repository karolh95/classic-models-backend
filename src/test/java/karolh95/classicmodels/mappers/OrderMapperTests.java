package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.repository.OrderRepository;

@SpringBootTest
public class OrderMapperTests {

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private OrderRepository repository;

	@Test
	@DisplayName("Should map Order to Dto")
	public void mapOrderToDto() {

		Optional<Order> optional = this.repository.findById(10103L);

		assertTrue(optional.isPresent(), "Order should exist");

		Order order = optional.get();
		DtoSimpleOrder dto = this.mapper.orderToDto(order);

		assertEquals(order.getOrderNumber(), dto.getOrderNumber(), "Order number should match");
		assertEquals(order.getOrderDate(), dto.getOrderDate(), "Order date should match");
		assertEquals(order.getRequiredDate(), dto.getRequiredDate(), "Required date should match");
		assertEquals(order.getShippedDate(), dto.getShippedDate(), "Shipped date should match");
		assertEquals(order.getStatus(), dto.getStatus(), "Status should match");
		assertEquals(order.getComments(), dto.getComments(), "Comments should match");
		assertEquals(order.getCustomer().getCustomerNumber(), dto.getCustomerNumber(), "Customer number should match");
	}
}