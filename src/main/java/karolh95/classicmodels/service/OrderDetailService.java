package karolh95.classicmodels.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.dto.query.OrderDetailQuery;
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

		// @formatter:off
		orderDetails.stream()
			.filter(distinctByKey(DtoOrderDetail::getProductCode))
			.forEach(dto->{

					dto.setOrderNumber(orderNumber);

					OrderDetail orderDetail = save(dto);

					if (orderDetail!=null){
						savedOrderDetails.add(orderDetail);
					}
			});
		// @formatter:on

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

	private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

		Set<Object> seen = new HashSet<>();

		return t -> seen.add(keyExtractor.apply(t));
	}

	public List<OrderDetailQuery.NumberOrderLineSubtotal> summary() {

		return repository.findAllSummary();
	}

	public List<Long> findByTotalGreaterThan(BigDecimal total) {

		return repository.findByTotalGreaterThan(total);
	}
}