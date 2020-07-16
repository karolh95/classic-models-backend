package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.mapper.OrderDetailMapper;
import karolh95.classicmodels.mapper.OrderDetailMapperImpl;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.mapper.OrderMapperImpl;
import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.utils.OrderUtil;

class OrderMapperTests {

	@Mock
	CustomerResolver customerResolver;

	@Mock
	OrderDetailMapper orderDetailMapper = new OrderDetailMapperImpl();

	@InjectMocks
	OrderMapper mapper = new OrderMapperImpl();

	@Nested
	@DisplayName("orderToDto tests")
	class OrderToDtoTests {

		@Test
		@DisplayName("Should not map from null Order to dto")
		void mapFromNullOrderToDto() {

			Order order = null;

			DtoSimpleOrder dtoSimpleOrder = mapper.orderToDto(order);

			assertNull(dtoSimpleOrder, "Dto simple order should be null");
		}

		@Test
		@DisplayName("Should map simple order to Dto")
		void mapOrderToDto() {

			Order order = OrderUtil.order();

			DtoSimpleOrder dtoOrder = mapper.orderToDto(order);

			OrderUtil.assertEquals(order, dtoOrder);
		}
	}

	@Nested
	@DisplayName("orderWithDetailsToDto")
	class OrderWithDetailsToDto {

		@BeforeEach
		void init() {
			MockitoAnnotations.initMocks(OrderMapperTests.this);

			when(orderDetailMapper.orderDetailsToDtos(anyList())).thenReturn(null);
		}

		@Test
		@DisplayName("Should not map from null order to dto")
		void mapNullOrderToDto() {

			Order order = null;

			DtoFullOrder dtoFullOrder = mapper.orderWithDetailsToDto(order);

			assertNull(dtoFullOrder, "Dto full order should be null");
		}

		@Test
		@DisplayName("Should map order to dto full order")
		void mapOrderToDtoFullOrder() {

			Order order = OrderUtil.order();

			DtoFullOrder dtoFullOrder = mapper.orderWithDetailsToDto(order);

			OrderUtil.assertEquals(order, dtoFullOrder);
		}
	}

	@Nested
	@DisplayName("ordersToDtos tests")
	class OrdersToDtosTests {

		List<Order> orders;
		List<DtoSimpleOrder> dtoSimpleOrders;

		@BeforeEach
		void init() {
			orders = OrderUtil.orders();
		}

		@Test
		@DisplayName("Should not map null orders to dtos")
		void mapNullOrdersToDtos() {

			orders = null;

			dtoSimpleOrders = mapper.ordersToDtos(orders);

			assertNull(dtoSimpleOrders, "Dto simple orders should be null");
		}

		@Test
		@DisplayName("Should map empty orders to dtos")
		void mapEmptyOrdersToDtos() {

			orders.clear();

			dtoSimpleOrders = mapper.ordersToDtos(orders);

			assertTrue(dtoSimpleOrders.isEmpty(), "Dto simple orders should be empty");
		}

		@Test
		@DisplayName("Should map orders to dtos")
		void mapOrdersToDtos() {

			dtoSimpleOrders = mapper.ordersToDtos(orders);

			for (int i = 0; i < orders.size(); i++) {

				Order order = orders.get(i);
				DtoSimpleOrder dtoSimpleOrder = dtoSimpleOrders.get(i);

				OrderUtil.assertEquals(order, dtoSimpleOrder);
			}
		}
	}

	@Nested
	@DisplayName("orderFromDto tests")
	class OrderFromDtoTests {

	}
}