package karolh95.classicmodels.controller.raport;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Order;
import karolh95.classicmodels.dto.query.OrderDetailQuery;
import karolh95.classicmodels.dto.query.OrderQuery;
import karolh95.classicmodels.service.raport.OrderRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Order.RAPORT)
public class OrderRaports {

	private final OrderRaport raport;

	@GetMapping(Order.Raport.SUMMARY)
	public ResponseEntity<List<OrderDetailQuery.NumberOrderLineSubtotal>> summary() {

		List<OrderDetailQuery.NumberOrderLineSubtotal> summary = raport.getDetailsSummary();

		return ResponseEntity.ok(summary);
	}

	@GetMapping(Order.Raport.STATUS)
	public ResponseEntity<List<OrderQuery.NumberStatus>> ordersOrderByStatus() {

		List<OrderQuery.NumberStatus> orders = raport.getOrdersOrderByState();

		return ResponseEntity.ok(orders);
	}

	@GetMapping(Order.Raport.TOTAL_GREATER_THAN)
	public List<OrderQuery.NumberStatusShippedCustomerNumber> ordersByOrderNumbers(
			@PathVariable BigDecimal total) {

		return raport.findByTotalGT(total);
	}

	@GetMapping(Order.Raport.REQUIRED_BETWEEN)
	public List<OrderQuery.NumberStatusRequired> findByRequiredDateBetween(@PathVariable Date from,
			@PathVariable Date to) {

		return raport.findByRequiredDateBetween(from, to);
	}

	@GetMapping(Order.Raport.REQUIRED_NOTBETWEEN)
	public List<OrderQuery.NumberStatusRequired> findByRequiredDateNotBetween(
			@PathVariable Date from, @PathVariable Date to) {

		return raport.findByRequiredDateNotBetween(from, to);
	}

	@GetMapping(Order.Raport.TOTAL)
	public List<OrderQuery.NumberStatusTotal> getordersWithTotal() {

		return raport.getOrdersWithTotal();
	}

	@GetMapping(Order.Raport.PRODUCT_PRICE)
	public List<OrderQuery.WithProductAndPrice> getOrdersWithProductAndPrice() {

		return raport.getOrdersWithProductAndPrice();
	}

	@GetMapping(Order.Raport.CUSTOMER_PRICE)
	public List<OrderQuery.WithCustomerAndPrice> getOrdersWithCustomerAndPrice() {

		return raport.getOrdersWithCustomerAndPrice();
	}

	@GetMapping(Order.Raport.BY_ORDERNUMBER)
	public List<OrderQuery.WithDetail> getOrdersWithProductCode(@PathVariable Long orderNumber) {

		return raport.getOrdersWithOrderNumber(orderNumber);
	}
}
