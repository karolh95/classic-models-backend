package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Order;

@Mapper(componentModel = "spring", uses = { CustomerResolver.class, OrderDetailMapper.class })
public interface OrderMapper {

	@Named("SimpleOrder")
	@Mapping(target = "customerNumber", source = "customer.customerNumber")
	DtoSimpleOrder orderToDto(Order order);

	@Named("FullOrder")
	@Mapping(target = "customerNumber", source = "customer.customerNumber")
	DtoFullOrder orderWithDetailsToDto(Order order);

	@IterableMapping(qualifiedByName = "SimpleOrder")
	List<DtoSimpleOrder> ordersToDtos(List<Order> orders);

	@Mapping(target = "orderNumber", ignore = true)
	@Mapping(target = "customer", source = "customerNumber")
	void updateFromDto(DtoFullOrder dtoOrder, @MappingTarget Order order);
}