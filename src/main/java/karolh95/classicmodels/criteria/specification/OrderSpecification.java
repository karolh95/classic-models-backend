package karolh95.classicmodels.criteria.specification;

import java.util.Date;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.Order_;

public class OrderSpecification {

	public static Specification<Order> requiredDateBetween(Date from, Date to) {

		return (order, query, builder) -> builder.between(getRequiredDate(order), from, to);
	}

	public static Specification<Order> requiredDateNotBetween(Date from, Date to) {

		return (order, query, builder) -> builder.between(getRequiredDate(order), from, to).not();
	}

	public static Specification<Order> hasOrderNumber(Long orderNumber) {

		return (order, query, builder) -> builder.equal(order.get(Order_.ORDER_NUMBER),
				orderNumber);
	}

	private static Path<Date> getRequiredDate(Root<Order> order) {

		return order.get(Order_.REQUIRED_DATE);
	}
}
