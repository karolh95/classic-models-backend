package karolh95.classicmodels.mappers.resolvers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.mapper.resolver.OrderResolver;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.repository.OrderRepository;
import karolh95.classicmodels.utils.OrderUtil;

class OrderResolverTests {

	@Mock
	OrderRepository repository;

	@InjectMocks
	OrderResolver resolver;

	Long orderNumber;
	Order order;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		orderNumber = 1L;
	}

	@Test
	@DisplayName("Should not map null orderNumber")
	void mapNullOrderNumber() {

		orderNumber = null;

		order = resolver.map(orderNumber);

		assertNull(order, "Order should be null");
	}

	@Test
	@DisplayName("Should not map orderNumber when Order not exists")
	void mapOrderNumberToNonExistingOrder() {

		when(repository.findById(anyLong())).thenReturn(empty());

		order = resolver.map(orderNumber);

		assertNull(order, "Order should be null");
	}

	@Test
	@DisplayName("Should map orderNumber when Order exists")
	void mapOrderNumberToExistingOrder() {

		when(repository.findById(anyLong())).thenReturn(optional());

		order = resolver.map(orderNumber);

		assertNotNull(order, "Order should not be null");
	}

	private Optional<Order> empty() {
		return Optional.empty();
	}

	private Optional<Order> optional() {
		return Optional.of(OrderUtil.order());
	}
}