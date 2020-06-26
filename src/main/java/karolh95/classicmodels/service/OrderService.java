package karolh95.classicmodels.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.mapper.OrderDetailMapper;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderMapper mapper;
	private final OrderDetailMapper detailMapper;
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

		addDetailsToOrder(dtoOrder, order);

		order = repository.save(order);

		return mapper.orderWithDetailsToDto(order);

	}

	private void addDetailsToOrder(DtoFullOrder dtoOrder, Order order) {

		order.setOrderDetails(new ArrayList<>());

		dtoOrder.getOrderDetails().forEach(detail -> {

			detail.setOrderNumber(order.getOrderNumber());
			OrderDetail orderDetail = detailMapper.orderDetailFromDto(detail);

			Product product = orderDetail.getProduct();

			int inStock = product.getQuantityInStock();
			int quantityOrdered = orderDetail.getQuantityOrdered();

			if (inStock >= quantityOrdered) {

				product.setQuantityInStock(inStock - quantityOrdered);
				order.addOrderDetails(orderDetail);
			}
		});
	}
}