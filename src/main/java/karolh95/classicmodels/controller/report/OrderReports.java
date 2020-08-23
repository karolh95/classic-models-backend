package karolh95.classicmodels.controller.report;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Order;
import karolh95.classicmodels.dto.projection.order.NumberStatus;
import karolh95.classicmodels.dto.projection.order.NumberStatusRequired;
import karolh95.classicmodels.dto.projection.order.NumberStatusShippedCustomer;
import karolh95.classicmodels.dto.projection.order.NumberStatusTotal;
import karolh95.classicmodels.dto.projection.order.WithCustomerPrice;
import karolh95.classicmodels.dto.projection.order.WithDetails;
import karolh95.classicmodels.dto.projection.order.WithProductPrice;
import karolh95.classicmodels.dto.projection.orderdetail.NumberOrderLineSubtotal;
import karolh95.classicmodels.service.report.OrderReport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Order.REPORT)
public class OrderReports {

	private final OrderReport report;

	@GetMapping(Order.Report.SUMMARY)
	public ResponseEntity<List<NumberOrderLineSubtotal>> summary() {

		List<NumberOrderLineSubtotal> summary = report.getDetailsSummary();

		return ResponseEntity.ok(summary);
	}

	@GetMapping(Order.Report.STATUS)
	public ResponseEntity<List<NumberStatus>> ordersOrderByStatus() {

		List<NumberStatus> orders = report.getOrdersOrderByState();

		return ResponseEntity.ok(orders);
	}

	@GetMapping(Order.Report.STATUS_DISTINCT)
	public ResponseEntity<List<NumberStatus>> ordersOrderByDistinctStatus() {

		List<NumberStatus> orders = report.getOrdersOrderByDistinctState();

		return ResponseEntity.ok(orders);
	}

	@GetMapping(Order.Report.TOTAL_GREATER_THAN)
	public List<NumberStatusShippedCustomer> ordersByOrderNumbers(@PathVariable BigDecimal total) {

		return report.findByTotalGT(total);
	}

	@GetMapping(Order.Report.REQUIRED_BETWEEN)
	public List<NumberStatusRequired> findByRequiredDateBetween(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {

		return report.findByRequiredDateBetween(from, to);
	}

	@GetMapping(Order.Report.REQUIRED_NOTBETWEEN)
	public List<NumberStatusRequired> findByRequiredDateNotBetween(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {

		return report.findByRequiredDateNotBetween(from, to);
	}

	@GetMapping(Order.Report.TOTAL)
	public List<NumberStatusTotal> getordersWithTotal() {

		return report.getOrdersWithTotal();
	}

	@GetMapping(Order.Report.PRODUCT_PRICE)
	public List<WithProductPrice> getOrdersWithProductAndPrice() {

		return report.getOrdersWithProductAndPrice();
	}

	@GetMapping(Order.Report.CUSTOMER_PRICE)
	public List<WithCustomerPrice> getOrdersWithCustomerAndPrice() {

		return report.getOrdersWithCustomerAndPrice();
	}

	@GetMapping(Order.Report.BY_ORDERNUMBER)
	public List<WithDetails> getOrdersWithProductCode(@PathVariable Long orderNumber) {

		return report.getOrdersWithOrderNumber(orderNumber);
	}
}
