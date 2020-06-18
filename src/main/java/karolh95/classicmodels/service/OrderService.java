package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoOrder;
import karolh95.classicmodels.mapper.OrderMapper;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderMapper mapper;
	private final OrderRepository repository;

	public List<DtoOrder> getAllOrders(){

		List<Order> orders = this.repository.findAll();
		return this.mapper.ordersToDtos(orders);
	}

	public DtoOrder getOrder(Long orderNumber){

		Optional<Order> optional = this.repository.findById(orderNumber);

		if (optional.isEmpty()){
			return null;
		}

		Order order = optional.get();
		return this.mapper.orderToDto(order);
	}
}