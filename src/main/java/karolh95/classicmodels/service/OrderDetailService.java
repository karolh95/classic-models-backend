package karolh95.classicmodels.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.mapper.OrderDetailMapper;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

	private final OrderDetailRepository repository;
	private final OrderDetailMapper mapper;

	public List<OrderDetail> save(Long orderNumber, List<DtoOrderDetail> orderDetails) {

		List<OrderDetail> savedOrderDetails = new ArrayList<>();

		orderDetails.forEach(detail -> {

			detail.setOrderNumber(orderNumber);

			OrderDetail orderDetail = save(detail);

			if (orderDetail != null) {
				savedOrderDetails.add(orderDetail);
			}
		});

		return savedOrderDetails;
	}

	public OrderDetail save(DtoOrderDetail dtoOrderDetail) {

		OrderDetail orderDetail = mapper.orderDetailFromDto(dtoOrderDetail);

		Product product = orderDetail.getProduct();

		int inStock = product.getQuantityInStock();
		int quantityOrdered = orderDetail.getQuantityOrdered();

		if (inStock >= quantityOrdered) {

			product.setQuantityInStock(inStock - quantityOrdered);

			return repository.save(orderDetail);
			
		} else {
			return null;
		}
	}
}