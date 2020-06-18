package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.mapper.OrderDetailMapper;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;
import karolh95.classicmodels.repository.OrderDetailRepository;

@SpringBootTest
public class OrderDetailMapperTests {
	
	@Autowired
	private OrderDetailMapper mapper;

	@Autowired
	private OrderDetailRepository repository;

	@Test
	@DisplayName("Should map OrderDetail to Dto")
	public void mapOrderDetailToDto(){

		OrderDetailPK id = new OrderDetailPK();
		id.setOrderNumber(10103L);
		id.setProductCode("S10_4962");

		Optional<OrderDetail> optional = this.repository.findById(id);

		assertTrue(optional.isPresent(), "Order detail should exist");

		OrderDetail orderDetail = optional.get();
		DtoOrderDetail dto = this.mapper.orderToDto(orderDetail);

		assertEquals(orderDetail.getOrderDetailPK().getOrderNumber(), dto.getOrderNumber(), "Order number should match");
		assertEquals(orderDetail.getOrderDetailPK().getProductCode(), dto.getProductCode(), "Product code should match");
		assertEquals(orderDetail.getQuantityOrdered(), dto.getQuantityOrdered(), "Quantity ordered should match");
		assertEquals(orderDetail.getPriceEach(), dto.getPriceEach(), "Price each should match");
		assertEquals(orderDetail.getOrderLineNumber(), dto.getOrderLineNumber(), "Order line number should match");
	}
}