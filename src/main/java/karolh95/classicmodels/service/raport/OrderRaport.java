package karolh95.classicmodels.service.raport;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.query.OrderDetailQuery;
import karolh95.classicmodels.dto.query.OrderQuery;
import karolh95.classicmodels.repository.OrderRepository;
import karolh95.classicmodels.service.OrderStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRaport {

	private final OrderRepository repository;
	private final OrderDetailRaport detailRaport;

	public List<OrderDetailQuery.NumberOrderLineSubtotal> getDetailsSummary() {

		return detailRaport.summary();
	}

	public List<OrderQuery.NumberStatus> getOrdersOrderByState() {

		// @formatter:off
		return repository.findBy()
				.stream()
				.sorted(Comparator.comparing(OrderQuery.NumberStatus::getStatus, OrderStatus.COMPARATOR))
				.collect(Collectors.toList())
		;
		// @formatter:on
	}

	public List<OrderQuery.NumberStatusShippedCustomerNumber> findByTotalGT(BigDecimal total) {

		List<Long> orderNumbers = detailRaport.findByTotalGreaterThan(total);

		return findByOrderNumbers(orderNumbers);
	}

	public List<OrderQuery.NumberStatusRequired> findByRequiredDateBetween(Date from, Date to) {

		return repository.findByRequiredDateBetween(from, to);
	}

	public List<OrderQuery.NumberStatusRequired> findByRequiredDateNotBetween(Date from, Date to) {

		return repository.findByRequiredDateBeforeOrRequiredDateAfter(from, to);
	}

	public List<OrderQuery.NumberStatusTotal> getOrdersWithTotal() {
		return repository.getOrdersWithTotal();
	};

	public List<OrderQuery.WithProductAndPrice> getOrdersWithProductAndPrice() {

		return repository.getOrdersWithProductAndPrice();
	}

	public List<OrderQuery.WithCustomerAndPrice> getOrdersWithCustomerAndPrice() {

		return repository.getOrdersWithCustomerAndPrice();
	}

	public List<OrderQuery.WithDetail> getOrdersWithOrderNumber(Long orderNumber) {

		return repository.findAllByOrderNumber(orderNumber);
	}

	private List<OrderQuery.NumberStatusShippedCustomerNumber> findByOrderNumbers(
			List<Long> orderNumbers) {

		return repository.findByOrderNumberIn(orderNumbers);
	}
}
