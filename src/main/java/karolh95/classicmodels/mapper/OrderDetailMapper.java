package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.model.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

	@Mapping(target = "orderNumber", source = "orderDetailPK.orderNumber")
	@Mapping(target = "productCode", source = "orderDetailPK.productCode")
	DtoOrderDetail orderToDto(OrderDetail orderDetail);

	List<DtoOrderDetail> orderDetailsToDtos(List<OrderDetail> orderDetails);
}