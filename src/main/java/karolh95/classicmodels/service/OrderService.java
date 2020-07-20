package karolh95.classicmodels.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.dto.query.OrderDetailSummary;
import karolh95.classicmodels.dto.query.OrderStatus;
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

		List<OrderDetail> orderDetails = detailService.save(order.getOrderNumber(), dtoOrder.getOrderDetails());

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

	public List<OrderDetailSummary> getDetailsSummary() {

		return detailService.summary();
	}

	public List<OrderStatus> getOrdersOrderByState() {

		// @formatter:off
		return repository.findBy()
				.stream()
				.sorted(Comparator.comparing(OrderStatus::getStatus, Status.COMPARATOR))
				.collect(Collectors.toList())
		;
		// @formatter:on
	}

	private enum Status {

		// @formatter:off
		In_PROCESS("In Process"), 
		ON_HOLD("On Hold"), 
		CANCELLED("Cancelled"), 
		RESOLVED("Resolved"),
		DISPUTED("Disputed"), 
		SHIPPED("Shipped");
		// @formatter:on

		public static final StatusComparator COMPARATOR = new StatusComparator();

		private String status;

		Status(String status) {
			this.status = status;
		}

		public static Status fromString(String text) {

			for (Status value : values()) {
				if (value.status.equalsIgnoreCase(text)) {
					return value;
				}
			}
			throw new IllegalArgumentException(String.format("Cannot cast '%s' to Status enum", text));
		}

		private static class StatusComparator implements Comparator<String> {

			@Override
			public int compare(String o1, String o2) {

				Status s1, s2;

				try {

					s1 = Status.fromString(o1);
					s2 = Status.fromString(o2);

				} catch (IllegalArgumentException e) {
					return 0;
				}

				return s1.compareTo(s2);
			}
		}
	};
}