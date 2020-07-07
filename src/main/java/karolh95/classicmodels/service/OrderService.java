package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.dto.DtoSimpleOrder;
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
}