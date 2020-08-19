package karolh95.classicmodels.criteria.specification;

import java.math.BigDecimal;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetail_;

public class OrderDetailSpecification {

	public static Specification<OrderDetail> subtotalGreaterThanMin(BigDecimal min) {

		return (orderDetail, query, builder) -> {

			Path<BigDecimal> quantityOrdered = orderDetail.get(OrderDetail_.QUANTITY_ORDERED);
			Path<BigDecimal> priceEach = orderDetail.get(OrderDetail_.PRICE_EACH);

			Expression<BigDecimal> subtotal = builder.prod(quantityOrdered, priceEach);
			Expression<BigDecimal> sum = builder.sum(subtotal);

			return builder.greaterThan(sum, min);
		};
	}
}
