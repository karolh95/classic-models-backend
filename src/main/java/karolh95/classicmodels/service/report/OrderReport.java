package karolh95.classicmodels.service.report;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.projection.order.NumberStatus;
import karolh95.classicmodels.dto.projection.order.NumberStatusRequired;
import karolh95.classicmodels.dto.projection.order.NumberStatusShippedCustomer;
import karolh95.classicmodels.dto.projection.order.NumberStatusTotal;
import karolh95.classicmodels.dto.projection.order.WithCustomerPrice;
import karolh95.classicmodels.dto.projection.order.WithDetails;
import karolh95.classicmodels.dto.projection.order.WithProductPrice;
import karolh95.classicmodels.dto.projection.orderdetail.NumberOrderLineSubtotal;
import karolh95.classicmodels.repository.OrderRepository;
import karolh95.classicmodels.service.OrderStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderReport {

	private final OrderRepository repository;
	private final OrderDetailReport detailReport;

	public List<NumberOrderLineSubtotal> getDetailsSummary() {

		return detailReport.summary();
	}

	public List<NumberStatus> getOrdersOrderByState() {

		// @formatter:off
		return repository.findStatus()
				.stream()
				.sorted(Comparator.comparing(NumberStatus::getStatus, OrderStatus.COMPARATOR))
				.collect(Collectors.toList())
		;
		// @formatter:on
	}

	public List<NumberStatus> getOrdersOrderByDistinctState() {

		return repository.findOrdersOrderByStatus();
	}

	public List<NumberStatusShippedCustomer> findByTotalGT(BigDecimal total) {

		List<Long> orderNumbers = detailReport.findByTotalGreaterThan(total);

		return findByOrderNumbers(orderNumbers);
	}

	public List<NumberStatusRequired> findByRequiredDateBetween(Date from, Date to) {

		return repository.findByRequiredDateBetween(from, to);
	}

	public List<NumberStatusRequired> findByRequiredDateNotBetween(Date from, Date to) {

		return repository.findByRequiredDateNotBetween(from, to);
	}

	public List<NumberStatusTotal> getOrdersWithTotal() {
		return repository.findOrdersWithTotal();
	};

	public List<WithProductPrice> getOrdersWithProductAndPrice() {

		return repository.findOrdersWithProductPrice();
	}

	public List<WithCustomerPrice> getOrdersWithCustomerAndPrice() {

		return repository.findOrdersWithCustomerPrice();
	}

	public List<WithDetails> getOrdersWithOrderNumber(Long orderNumber) {

		return repository.findOrdersByOrderNumber(orderNumber);
	}

	private List<NumberStatusShippedCustomer> findByOrderNumbers(List<Long> orderNumbers) {

		return repository.findByOrderNumberIn(orderNumbers);
	}
}
