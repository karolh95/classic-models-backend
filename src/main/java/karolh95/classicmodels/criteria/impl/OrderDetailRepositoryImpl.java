package karolh95.classicmodels.criteria.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.OrderDetailRepositoryCustom;
import karolh95.classicmodels.criteria.specification.OrderDetailSpecification;
import karolh95.classicmodels.dto.projection.orderdetail.NumberOrderLineSubtotal;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetail_;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderDetailRepositoryImpl implements OrderDetailRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Long> findByTotalMin(BigDecimal min) {

		CriteriaQuery<Long> query = builder().createQuery(Long.class);
		Root<OrderDetail> orderDetail = query.from(OrderDetail.class);

		Path<Long> orderNumber = orderDetail.get(OrderDetail_.ORDER_NUMBER);

		Predicate sumGreaterThan = OrderDetailSpecification.subtotalGreaterThanMin(min)
				.toPredicate(orderDetail, query, builder());

		query.select(orderNumber);
		query.groupBy(orderNumber);
		query.having(sumGreaterThan);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<NumberOrderLineSubtotal> findAllSummary() {

		CriteriaQuery<NumberOrderLineSubtotal> query =
				builder().createQuery(NumberOrderLineSubtotal.class);
		Root<OrderDetail> orderDetail = query.from(OrderDetail.class);

		Path<Long> orderNumber = orderDetail.get(OrderDetail_.ORDER_NUMBER);
		Path<Short> orderLineNumber = orderDetail.get(OrderDetail_.ORDER_LINE_NUMBER);

		Path<BigDecimal> quantityOrdered = orderDetail.get(OrderDetail_.QUANTITY_ORDERED);
		Path<BigDecimal> priceEach = orderDetail.get(OrderDetail_.PRICE_EACH);

		Expression<BigDecimal> subtotal = builder().prod(quantityOrdered, priceEach);
		Order bySubtotal = builder().desc(subtotal);

		query.multiselect(orderNumber, orderLineNumber, subtotal.alias("subtotal"));
		query.orderBy(bySubtotal);

		return entityManager.createQuery(query).getResultList();
	}

	private CriteriaBuilder builder() {
		return entityManager.getCriteriaBuilder();
	}
}
