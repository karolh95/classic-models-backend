package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.mapper.OrderDetailMapper;
import karolh95.classicmodels.mapper.OrderDetailMapperImpl;
import karolh95.classicmodels.mapper.resolver.OrderResolver;
import karolh95.classicmodels.mapper.resolver.ProductResolver;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.utils.OrderDetailUtil;
import karolh95.classicmodels.utils.OrderUtil;
import karolh95.classicmodels.utils.ProductUtil;

class OrderDetailMapperTests {

	@Mock
	OrderResolver orderResolver;

	@Mock
	ProductResolver productResolver;

	@InjectMocks
	OrderDetailMapper mapper = new OrderDetailMapperImpl();

	@Nested
	@DisplayName("orderToDto tests")
	class OrderToDtoTests {

		@Test
		@DisplayName("Should not map null OrderDetail")
		void mapNullOrderDetailToDto() {

			OrderDetail orderDetail = null;

			DtoOrderDetail dtoOrderDetail = mapper.orderToDto(orderDetail);

			assertNull(dtoOrderDetail, "Dto order detail should be null");
		}

		@Test
		@DisplayName("Should map OrderDetail to Dto")
		void mapOrderDetailToDto() {

			OrderDetail orderDetail = OrderDetailUtil.orderDetail();

			DtoOrderDetail dtoOrderDetail = mapper.orderToDto(orderDetail);

			OrderDetailUtil.assertEquals(orderDetail, dtoOrderDetail);
		}
	}

	@Nested
	@DisplayName("orderDetailsToDtos")
	class orderDetailsToDtos {

		List<OrderDetail> orderDetails;
		List<DtoOrderDetail> dtoOrderDetails;

		@BeforeEach
		void init() {
			orderDetails = OrderDetailUtil.orderDetails();
		}

		@Test
		@DisplayName("Should not map null orderDetails to dtos")
		void mapNullOrderDetailsToDtos() {

			orderDetails = null;

			dtoOrderDetails = mapper.orderDetailsToDtos(orderDetails);

			assertNull(dtoOrderDetails, "Dto order details should be null");
		}

		@Test
		@DisplayName("Should map empty orderDetails to dtos")
		void mapEmptyOrderDetailsToDtos() {

			orderDetails.clear();

			dtoOrderDetails = mapper.orderDetailsToDtos(orderDetails);

			assertTrue(dtoOrderDetails.isEmpty(), "Dto orderDetails should be empty");
		}

		@Test
		@DisplayName("Should map orderDetails to dtos")
		void mapOrderDetailsToDtos() {

			dtoOrderDetails = mapper.orderDetailsToDtos(orderDetails);

			for (int i = 0; i < orderDetails.size(); i++) {

				OrderDetail orderDetail = orderDetails.get(i);
				DtoOrderDetail dtoOrderDetail = dtoOrderDetails.get(i);

				OrderDetailUtil.assertEquals(orderDetail, dtoOrderDetail);
			}
		}
	}

	@Nested
	@DisplayName("orderDetailFromDto tests")
	class OrderDetailFromDtosTests {

		@BeforeEach
		void init() {

			MockitoAnnotations.initMocks(OrderDetailMapperTests.this);

			when(orderResolver.map(anyLong())).thenReturn(OrderUtil.order());
			when(productResolver.map(anyString())).thenReturn(ProductUtil.product());
		}

		@Test
		@DisplayName("Should not map from null dto")
		void mapFromNullDto() {

			DtoOrderDetail dtoOrderDetail = null;

			OrderDetail orderDetail = mapper.orderDetailFromDto(dtoOrderDetail);

			assertNull(orderDetail, "Order details should be null");
		}

		@Test
		@DisplayName("Should map from dto")
		void mapFromDto() {

			DtoOrderDetail dtoOrderDetail = OrderDetailUtil.dtoOrderDetail();

			OrderDetail orderDetail = mapper.orderDetailFromDto(dtoOrderDetail);

			OrderDetailUtil.assertEquals(orderDetail, dtoOrderDetail);
		}

	}
}