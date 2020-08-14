package karolh95.classicmodels.criteria;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import karolh95.classicmodels.dto.projection.order.NumberStatus;
import karolh95.classicmodels.dto.projection.order.NumberStatusRequired;
import karolh95.classicmodels.dto.projection.order.NumberStatusShippedCustomer;
import karolh95.classicmodels.dto.projection.order.NumberStatusTotal;
import karolh95.classicmodels.dto.projection.order.WithCustomerPrice;
import karolh95.classicmodels.dto.projection.order.WithDetails;
import karolh95.classicmodels.dto.projection.order.WithProductPrice;

public interface OrderRepositoryCustom {

	List<NumberStatus> findStatus();

	List<NumberStatus> findOrdersOrderByStatus();

	List<NumberStatusRequired> findByRequiredDateBetween(Date from, Date to);

	List<NumberStatusRequired> findByRequiredDateNotBetween(Date from, Date to);

	List<NumberStatusTotal> findOrdersWithTotal();

	List<WithProductPrice> findOrdersWithProductPrice();

	List<WithDetails> findOrdersByOrderNumber(Long number);

	List<WithCustomerPrice> findOrdersWithCustomerPrice();

	List<NumberStatusShippedCustomer> findByOrderNumberIn(Collection<Long> numbers);
}
