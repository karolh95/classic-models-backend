package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;
import karolh95.classicmodels.model.Product;

public final class OrderDetailUtil {

	private static final int QUANTITY_ORDERED = 1024;
	private static final BigDecimal PRICE_EACH = new BigDecimal(10.0);
	private static final short ORDER_LINE_NUMBER = 1;

	private static final int PRODUCT_CODE_MIN = 1;
	private static final int PRODUCT_CODE_MAX = 6;
	private static final Long ORDER_NUMBER = 1L;

	private OrderDetailUtil() {

	}

	public static OrderDetail orderDetail() {

		return orderDetail(order(), product());
	}

	private static OrderDetail orderDetail(Order order, Product product) {

		OrderDetailPK pk = new OrderDetailPK(order.getOrderNumber(), product.getProductCode());

		OrderDetail orderDetail = new OrderDetail();

		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantityOrdered(QUANTITY_ORDERED);
		orderDetail.setOrderDetailPK(pk);
		orderDetail.setPriceEach(PRICE_EACH);
		orderDetail.setOrderLineNumber(ORDER_LINE_NUMBER);

		return orderDetail;
	}

	public static DtoOrderDetail dtoOrderDetail() {

		DtoOrderDetail orderDetail = new DtoOrderDetail();

		orderDetail.setOrderNumber(ORDER_NUMBER);
		orderDetail.setProductCode(String.valueOf(PRODUCT_CODE_MIN));
		orderDetail.setQuantityOrdered(QUANTITY_ORDERED);
		orderDetail.setPriceEach(PRICE_EACH);
		orderDetail.setOrderLineNumber(ORDER_LINE_NUMBER);

		return orderDetail;
	}

	private static Order order() {

		Order order = new Order();

		order.setOrderNumber(ORDER_NUMBER);

		return order;
	}

	private static Product product() {

		return product(PRODUCT_CODE_MIN);
	}

	private static Product product(int productCode) {

		Product product = new Product();

		product.setProductCode(String.valueOf(productCode));

		return product;
	}

	public static List<OrderDetail> orderDetails() {

		Order order = OrderUtil.order();

		List<OrderDetail> orderDetails = new ArrayList<>();

		for (int productCode = PRODUCT_CODE_MIN; productCode < PRODUCT_CODE_MAX; productCode++) {

			Product product = product(productCode);

			orderDetails.add(orderDetail(order, product));
		}

		return orderDetails;
	}

	public static void assertEquals(OrderDetail orderDetail, DtoOrderDetail dtoOrderDetail) {

		assertNotNull(orderDetail, "Order detail should not be null");
		assertNotNull(dtoOrderDetail, "DTO order detail should not be null");

		assertOrderDetailPKsEquals(orderDetail, dtoOrderDetail);
		assertModifiableFieldsEquals(orderDetail, dtoOrderDetail);
		assertOrdersNumberEquals(orderDetail.getOrder(), dtoOrderDetail.getOrderNumber());
		assertProductsCodesEquals(orderDetail.getProduct(), dtoOrderDetail.getProductCode());
	}

	private static void assertOrderDetailPKsEquals(OrderDetail orderDetail, DtoOrderDetail dtoOrderDetail) {

		OrderDetailPK pk = orderDetail.getOrderDetailPK();

		assertNotNull(pk, "Order detail PK should not be null");
		Assertions.assertEquals(pk.getOrderNumber(), dtoOrderDetail.getOrderNumber(), "Order number should match");
		Assertions.assertEquals(pk.getProductCode(), dtoOrderDetail.getProductCode(), "Product code should match");
	}

	private static void assertModifiableFieldsEquals(OrderDetail orderDetail, DtoOrderDetail dtoOrderDetail) {

		Assertions.assertEquals(orderDetail.getQuantityOrdered(), dtoOrderDetail.getQuantityOrdered(),
				"Quantity ordered should match");
		Assertions.assertEquals(orderDetail.getPriceEach(), dtoOrderDetail.getPriceEach(), "Price each should match");
		Assertions.assertEquals(orderDetail.getOrderLineNumber(), dtoOrderDetail.getOrderLineNumber(),
				"Order line number should match");
	}

	private static void assertOrdersNumberEquals(Order order, Long orderNumber) {

		assertNotNull(order, "Order should not be null");
		Assertions.assertEquals(order.getOrderNumber(), orderNumber, "Order number should match");
	}

	private static void assertProductsCodesEquals(Product product, String productCode) {

		assertNotNull(product, "Product should not be null");
		Assertions.assertEquals(product.getProductCode(), productCode);
	}

}