package karolh95.classicmodels.mapper.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.repository.OrderRepository;

@Component
public class OrderResolver {

	@Autowired
	private OrderRepository repository;

	public Order map(Long orderNumber) {

		Optional<Order> optional = repository.findById(orderNumber);

		if (optional.isPresent()) {

			return optional.get();
			
		} else {

			return null;
		}
	}
}