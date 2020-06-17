package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DTOOrder;
import karolh95.classicmodels.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	@Mapping(target = "customerNumber", source = "customer.customerNumber")
	DTOOrder orderToDto(Order order);

	List<DTOOrder> ordersToDtos(List<Order> orders);
}