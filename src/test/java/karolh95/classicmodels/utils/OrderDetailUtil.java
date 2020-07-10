package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;
import karolh95.classicmodels.model.Product;

public class OrderDetailUtil {

	public static OrderDetail orderDetail() {

		OrderDetail orderDetail = new OrderDetail();

		Order order = OrderUtil.order();
		Product product = ProductUtil.product();
		OrderDetailPK pk = new OrderDetailPK(order.getOrderNumber(), product.getProductCode());

		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantityOrdered(1024);
		orderDetail.setOrderDetailPK(pk);
		orderDetail.setPriceEach(new BigDecimal(10.0));
		orderDetail.setOrderLineNumber((short) 1);

		return orderDetail;
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
}