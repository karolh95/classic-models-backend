package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.mapper.OrderDetailMapper;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;
import karolh95.classicmodels.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

	private final OrderDetailMapper mapper;
	private final OrderDetailRepository repository;

	public List<DtoOrderDetail> getAllOrderDetails() {

		List<OrderDetail> orderDetails = repository.findAll();
		return mapper.orderDetailsToDtos(orderDetails);
	}

	public List<DtoOrderDetail> getOrderDetails(Long orderNumber) {

		List<OrderDetail> orderDetails = getOrderDetailsByOrderNumber(orderNumber);
		return mapper.orderDetailsToDtos(orderDetails);
	}

	private List<OrderDetail> getOrderDetailsByOrderNumber(Long orderNumber) {

		return repository.findByOrderDetailPKOrderNumber(orderNumber);
	}

	public DtoOrderDetail getOrderDetail(Long orderNumber, String productCode) {

		OrderDetailPK pk = new OrderDetailPK(orderNumber, productCode);

		Optional<OrderDetail> optional = repository.findById(pk);

		if (optional.isEmpty()) {
			return null;
		}

		OrderDetail orderDetail = optional.get();
		return mapper.orderToDto(orderDetail);
	}

	public DtoOrderDetail saveOrderDetail(DtoOrderDetail dtoOrderDetail) {

		OrderDetail orderDetail = getOne(dtoOrderDetail.getOrderNumber(), dtoOrderDetail.getProductCode());

		mapper.updateFromDto(dtoOrderDetail, orderDetail);

		if (!orderDetail.hasValidIds()) {
			return null;
		}

		orderDetail = repository.save(orderDetail);

		return mapper.orderToDto(orderDetail);
	}

	private OrderDetail getOne(Long orderNumber, String productCode) {

		OrderDetailPK pk = new OrderDetailPK(orderNumber, productCode);
		Optional<OrderDetail> optional = repository.findById(pk);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			OrderDetail orderDetail = new OrderDetail();

			orderDetail.setOrderDetailPK(pk);

			return orderDetail;
		}
	}
}