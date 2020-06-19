package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import karolh95.classicmodels.dto.DtoOrder;
import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Order;

@Mapper(componentModel = "spring", uses = { CustomerResolver.class })
public interface OrderMapper {

	@Mapping(target = "customerNumber", source = "customer.customerNumber")
	DtoOrder orderToDto(Order order);

	List<DtoOrder> ordersToDtos(List<Order> orders);

	@Mapping(target = "orderNumber", ignore = true)
	@Mapping(target = "customer", source = "customerNumber")
	void updateFromDto(DtoOrder dtoOrder, @MappingTarget Order order);
}