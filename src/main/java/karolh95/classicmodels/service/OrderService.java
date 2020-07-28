package karolh95.classicmodels.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.dto.query.OrderDetailQuery;
import karolh95.classicmodels.dto.query.OrderQuery;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderMapper mapper;
	private final OrderDetailService detailService;
	private final PaymentService paymentService;
	private final OrderRepository repository;

	public List<DtoSimpleOrder> getAllOrders() {

		List<Order> orders = repository.findAll();
		return mapper.ordersToDtos(orders);
	}

	public DtoFullOrder getOrder(Long orderNumber) {

		Optional<Order> optional = repository.findById(orderNumber);

		if (optional.isEmpty()) {
			return null;
		}

		Order order = optional.get();

		order.sortOrderDetails();

		return mapper.orderWithDetailsToDto(order);
	}

	public DtoFullOrder saveOrder(DtoFullOrder dtoOrder) {

		Order order = mapper.orderFromDto(dtoOrder);

		if (!order.hasValidIds()) {
			return null;
		}

		order = repository.save(order);

		List<OrderDetail> orderDetails =
				detailService.save(order.getOrderNumber(), dtoOrder.getOrderDetails());

		order.setOrderDetails(orderDetails);

		return mapper.orderWithDetailsToDto(order);

	}

	public DtoPayment generatePayment(DtoFullOrder dtoOrder) {

		Order order = mapper.orderFromDto(dtoOrder);

		if (!order.hasValidIds()) {
			return null;
		}

		return paymentService.generatePayment(order);
	}

	public DtoPayment makePayment(DtoPayment payment) {

		return paymentService.savePayment(payment);
	}

	public List<OrderDetailQuery.NumberOrderLineSubtotal> getDetailsSummary() {

		return detailService.summary();
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

		List<Long> orderNumbers = detailService.findByTotalGreaterThan(total);

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

	private List<OrderQuery.NumberStatusShippedCustomerNumber> findByOrderNumbers(
			List<Long> orderNumbers) {

		return repository.findByOrderNumberIn(orderNumbers);
	}
}
