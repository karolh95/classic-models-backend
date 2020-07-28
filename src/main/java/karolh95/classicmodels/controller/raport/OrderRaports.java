package karolh95.classicmodels.controller.raport;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.OrderDetailQuery;
import karolh95.classicmodels.dto.query.OrderQuery;
import karolh95.classicmodels.service.OrderService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("order/raports")
public class OrderRaports {

	private final OrderService service;

	@GetMapping("summary")
	public ResponseEntity<List<OrderDetailQuery.NumberOrderLineSubtotal>> summary() {

		List<OrderDetailQuery.NumberOrderLineSubtotal> summary = service.getDetailsSummary();

		return ResponseEntity.ok(summary);
	}

	@GetMapping("status")
	public ResponseEntity<List<OrderQuery.NumberStatus>> ordersOrderByStatus() {

		List<OrderQuery.NumberStatus> orders = service.getOrdersOrderByState();

		return ResponseEntity.ok(orders);
	}

	@GetMapping("getByTotalGreaterThan/{total}")
	public List<OrderQuery.NumberStatusShippedCustomerNumber> ordersByOrderNumbers(
			@PathVariable BigDecimal total) {

		return service.findByTotalGT(total);
	}

	@GetMapping("getByRequiredDateBetween/{from}/{to}")
	public List<OrderQuery.NumberStatusRequired> findByRequiredDateBetween(@PathVariable Date from,
			@PathVariable Date to) {

		return service.findByRequiredDateBetween(from, to);
	}

	@GetMapping("getByRequiredDateNotBetween/{from}/{to}")
	public List<OrderQuery.NumberStatusRequired> findByRequiredDateNotBetween(
			@PathVariable Date from, @PathVariable Date to) {

		return service.findByRequiredDateNotBetween(from, to);
	}
}
