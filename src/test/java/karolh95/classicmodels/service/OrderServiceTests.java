package karolh95.classicmodels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.sql.Date;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.repository.OrderRepository;

@SpringBootTest
@Disabled
public class OrderServiceTests {

	private static final Long NOW = System.currentTimeMillis();
	private static final Long DAY = 24L * 60L * 60L * 1000L;

	@MockBean
	private OrderRepository repository;

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private OrderService service;

	@Test
	@DisplayName("Should create new Order")
	public void createOrder() {

		when(repository.getOne(anyLong())).thenThrow(new EntityNotFoundException());
		when(repository.save(any(Order.class))).thenReturn(order());

		DtoFullOrder order = DtoFullOrder();
		DtoFullOrder response = service.saveOrder(order);

		assertEquals(order, response, "Order and response should match");
	}

	@Test
	@DisplayName("Should update Order")
	public void updateOrder() {

		when(repository.getOne(anyLong())).thenReturn(order());
		when(repository.save(any(Order.class))).thenReturn(modifiedOrder());

		DtoFullOrder order = modifiedDtoFullOrder();
		DtoFullOrder response = service.saveOrder(order);

		assertEquals(order, response, "Order and response should match");
	}

	private Order order() {

		Order order = new Order();

		order.setOrderNumber(1L);
		order.setOrderDate(new Date(NOW));
		order.setRequiredDate(new Date(NOW + 7 * DAY));
		order.setShippedDate(new Date(NOW + 3 * DAY));
		order.setStatus("status");
		order.setComments("comments");
		order.setCustomer(customer());

		return order;
	}

	private Customer customer() {

		Customer customer = new Customer();

		customer.setCustomerNumber(1L);

		return customer;
	}

	private DtoFullOrder DtoFullOrder() {

		return mapper.orderWithDetailsToDto(order());
	}

	private Order modifiedOrder() {

		Order order = order();

		order.setStatus("newStatus");
		order.setComments("newComments");

		return order;
	}

	private DtoFullOrder modifiedDtoFullOrder() {

		return mapper.orderWithDetailsToDto(modifiedOrder());
	}
}
