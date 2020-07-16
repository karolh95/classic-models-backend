package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.mapper.resolver.OrderResolver;
import karolh95.classicmodels.mapper.resolver.ProductResolver;
import karolh95.classicmodels.model.OrderDetail;

@Mapper(componentModel = "spring", uses = { OrderResolver.class, ProductResolver.class })
public interface OrderDetailMapper {

	@Mapping(target = "orderNumber", source = "orderDetailPK.orderNumber")
	@Mapping(target = "productCode", source = "orderDetailPK.productCode")
	DtoOrderDetail orderToDto(OrderDetail orderDetail);

	List<DtoOrderDetail> orderDetailsToDtos(List<OrderDetail> orderDetails);

	@Mapping(target = "order", source = "orderNumber")
	@Mapping(target = "product", source = "productCode")
	@Mapping(target = "orderDetailPK.orderNumber", source = "orderNumber")
	@Mapping(target = "orderDetailPK.productCode", source = "productCode")
	OrderDetail orderDetailFromDto(DtoOrderDetail dtoOrderDetail);
}