package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderMapper mapper;
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

		Order order = getOne(dtoOrder.getOrderNumber());

		mapper.updateFromDto(dtoOrder, order);

		if (!order.hasValidIds()) {
			return null;
		}

		order = repository.save(order);

		return mapper.orderWithDetailsToDto(order);

	}

	private Order getOne(Long orderNumber) {

		Optional<Order> optional = repository.findById(orderNumber);

		if (optional.isPresent()) {
			return optional.get();

		} else {

			Order order = new Order();

			order.setOrderNumber(orderNumber);

			return order;
		}
	}
}