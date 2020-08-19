package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.mapper.resolver.OrderResolver;
import karolh95.classicmodels.mapper.resolver.ProductResolver;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK_;
import karolh95.classicmodels.model.OrderDetail_;

@Mapper(componentModel = "spring", uses = {OrderResolver.class, ProductResolver.class})
public interface OrderDetailMapper {

	String PK_ORDER_NUMBER = OrderDetail_.ORDER_DETAIL_PK + "." + OrderDetailPK_.ORDER_NUMBER;
	String PK_PRODUCT_CODE = OrderDetail_.ORDER_DETAIL_PK + "." + OrderDetailPK_.PRODUCT_CODE;

	DtoOrderDetail orderToDto(OrderDetail orderDetail);

	List<DtoOrderDetail> orderDetailsToDtos(List<OrderDetail> orderDetails);

	@Mapping(target = OrderDetail_.ORDER, source = OrderDetail_.ORDER_NUMBER)
	@Mapping(target = OrderDetail_.PRODUCT, source = OrderDetail_.PRODUCT_CODE)
	@Mapping(target = PK_ORDER_NUMBER, source = OrderDetail_.ORDER_NUMBER)
	@Mapping(target = PK_PRODUCT_CODE, source = OrderDetail_.PRODUCT_CODE)
	OrderDetail orderDetailFromDto(DtoOrderDetail dtoOrderDetail);
}
