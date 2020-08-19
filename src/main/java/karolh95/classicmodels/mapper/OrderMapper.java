package karolh95.classicmodels.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.Order_;

@Mapper(componentModel = "spring", uses = {CustomerResolver.class, OrderDetailMapper.class})
public interface OrderMapper {

	@Named("SimpleOrder")
	DtoSimpleOrder orderToDto(Order order);

	@Named("FullOrder")
	DtoFullOrder orderWithDetailsToDto(Order order);

	@IterableMapping(qualifiedByName = "SimpleOrder")
	List<DtoSimpleOrder> ordersToDtos(List<Order> orders);

	@Mapping(target = Order_.ORDER_NUMBER, ignore = true)
	@Mapping(target = Order_.CUSTOMER, source = Order_.CUSTOMER_NUMBER)
	@Mapping(target = Order_.ORDER_DETAILS, ignore = true)
	Order orderFromDto(DtoFullOrder dtoOrder);
}
