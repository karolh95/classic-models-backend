package karolh95.classicmodels.criteria.impl;

import static karolh95.classicmodels.service.OrderStatus.CANCELLED;
import static karolh95.classicmodels.service.OrderStatus.DISPUTED;
import static karolh95.classicmodels.service.OrderStatus.In_PROCESS;
import static karolh95.classicmodels.service.OrderStatus.ON_HOLD;
import static karolh95.classicmodels.service.OrderStatus.RESOLVED;
import static karolh95.classicmodels.service.OrderStatus.SHIPPED;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.OrderRepositoryCustom;
import karolh95.classicmodels.criteria.specification.OrderSpecification;
import karolh95.classicmodels.dto.projection.order.NumberStatus;
import karolh95.classicmodels.dto.projection.order.NumberStatusRequired;
import karolh95.classicmodels.dto.projection.order.NumberStatusShippedCustomer;
import karolh95.classicmodels.dto.projection.order.NumberStatusTotal;
import karolh95.classicmodels.dto.projection.order.WithCustomerPrice;
import karolh95.classicmodels.dto.projection.order.WithDetails;
import karolh95.classicmodels.dto.projection.order.WithProductPrice;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Customer_;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetail_;
import karolh95.classicmodels.model.Order_;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Product_;
import karolh95.classicmodels.service.OrderStatus;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	public List<NumberStatus> findStatus() {

		CriteriaQuery<NumberStatus> query = builder().createQuery(NumberStatus.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<String> status = order.get(Order_.STATUS);

		query.multiselect(orderNumber, status);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<NumberStatus> findOrdersOrderByStatus() {

		final String FIELD = "FIELD";

		CriteriaQuery<NumberStatus> query = builder().createQuery(NumberStatus.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<String> status = order.get(Order_.STATUS);

		Map<OrderStatus, ParameterExpression<String>> params = new HashMap<>();
		List<OrderStatus> orderStatuses = Arrays.asList(OrderStatus.values());

		orderStatuses.forEach(orderStatus -> params.put(orderStatus, createStringParameter()));

		// @formatter:off
		Expression<Integer> field = builder().function(FIELD, Integer.class, status,
				params.get(In_PROCESS), 
				params.get(ON_HOLD), 
				params.get(CANCELLED),
				params.get(RESOLVED), 
				params.get(DISPUTED), 
				params.get(SHIPPED));
		// @formatter:on

		query.multiselect(orderNumber, status);
		query.orderBy(builder().asc(field));

		TypedQuery<NumberStatus> typedQuery = entityManager.createQuery(query);

		orderStatuses.forEach(orderStatus -> typedQuery.setParameter(params.get(orderStatus),
				orderStatus.getStatus()));

		return typedQuery.getResultList();
	}

	@Override
	public List<NumberStatusRequired> findByRequiredDateBetween(Date from, Date to) {

		Specification<Order> requiredDateBetween = OrderSpecification.requiredDateBetween(from, to);

		return findByRequiredDate(requiredDateBetween);
	}

	@Override
	public List<NumberStatusRequired> findByRequiredDateNotBetween(Date from, Date to) {

		Specification<Order> requiredDateNotBetween =
				OrderSpecification.requiredDateNotBetween(from, to);

		return findByRequiredDate(requiredDateNotBetween);
	}

	@Override
	public List<NumberStatusTotal> findOrdersWithTotal() {

		CriteriaQuery<NumberStatusTotal> query = builder().createQuery(NumberStatusTotal.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<String> status = order.get(Order_.STATUS);

		Join<Order, OrderDetail> orderDetails = order.join(Order_.ORDER_DETAILS);

		Path<BigDecimal> quantityOrdered = orderDetails.get(OrderDetail_.QUANTITY_ORDERED);
		Path<BigDecimal> priceEach = orderDetails.get(OrderDetail_.PRICE_EACH);

		Expression<BigDecimal> total = builder().prod(quantityOrdered, priceEach);
		Expression<BigDecimal> sum = builder().sum(total);

		query.multiselect(orderNumber, status, sum);
		query.groupBy(orderNumber);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithProductPrice> findOrdersWithProductPrice() {

		CriteriaQuery<WithProductPrice> query = builder().createQuery(WithProductPrice.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<Date> orderDate = order.get(Order_.ORDER_DATE);

		Join<Order, OrderDetail> orderDetails = order.join(Order_.ORDER_DETAILS);

		Path<Short> orderLineNumber = orderDetails.get(OrderDetail_.ORDER_LINE_NUMBER);
		Path<BigDecimal> priceEach = orderDetails.get(OrderDetail_.PRICE_EACH);
		Path<BigDecimal> quantityOrdered = orderDetails.get(OrderDetail_.QUANTITY_ORDERED);

		Join<OrderDetail, Product> product = orderDetails.join(OrderDetail_.PRODUCT);

		Path<String> productName = product.get(Product_.PRODUCT_NAME);

		query.multiselect(orderNumber, orderDate, orderLineNumber, priceEach, quantityOrdered,
				productName);
		query.orderBy(builder().asc(orderNumber), builder().asc(orderLineNumber));

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithDetails> findOrdersByOrderNumber(Long number) {

		CriteriaQuery<WithDetails> query = builder().createQuery(WithDetails.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<Long> customerNumber = order.get(Order_.CUSTOMER_NUMBER);

		Join<Order, OrderDetail> orderDetails = order.join(Order_.ORDER_DETAILS);

		Path<String> productCode = orderDetails.get(OrderDetail_.PRODUCT_CODE);

		Predicate hasOrderNumber =
				OrderSpecification.hasOrderNumber(number).toPredicate(order, query, builder());

		query.multiselect(orderNumber, customerNumber, productCode);
		query.where(hasOrderNumber);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithCustomerPrice> findOrdersWithCustomerPrice() {

		CriteriaQuery<WithCustomerPrice> query = builder().createQuery(WithCustomerPrice.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<Date> orderDate = order.get(Order_.ORDER_DATE);

		Join<Order, OrderDetail> orderDetails = order.join(Order_.ORDER_DETAILS);

		Path<Short> orderLineNumber = orderDetails.get(OrderDetail_.ORDER_LINE_NUMBER);
		Path<BigDecimal> priceEach = orderDetails.get(OrderDetail_.PRICE_EACH);
		Path<BigDecimal> quantityOrdered = orderDetails.get(OrderDetail_.QUANTITY_ORDERED);

		Join<OrderDetail, Product> product = orderDetails.join(OrderDetail_.PRODUCT);

		Path<String> productName = product.get(Product_.PRODUCT_NAME);

		Join<Order, Customer> customer = order.join(Order_.CUSTOMER);

		Path<String> customerName = customer.get(Customer_.CUSTOMER_NAME);

		query.multiselect(orderNumber, orderDate, orderLineNumber, priceEach, quantityOrdered,
				productName, customerName);
		query.orderBy(builder().asc(orderNumber), builder().asc(orderLineNumber));

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<NumberStatusShippedCustomer> findByOrderNumberIn(Collection<Long> numbers) {

		CriteriaQuery<NumberStatusShippedCustomer> query =
				builder().createQuery(NumberStatusShippedCustomer.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<String> status = order.get(Order_.STATUS);
		Path<Date> shippedDate = order.get(Order_.SHIPPED_DATE);
		Path<Long> customerNumber = order.get(Order_.CUSTOMER_NUMBER);

		query.multiselect(orderNumber, status, shippedDate, customerNumber);
		query.orderBy(builder().asc(orderNumber));

		return entityManager.createQuery(query).getResultList();
	}

	private CriteriaBuilder builder() {
		return entityManager.getCriteriaBuilder();
	}

	private ParameterExpression<String> createStringParameter() {
		return builder().parameter(String.class);
	}

	private List<NumberStatusRequired> findByRequiredDate(Specification<Order> specification) {

		CriteriaQuery<NumberStatusRequired> query =
				builder().createQuery(NumberStatusRequired.class);
		Root<Order> order = query.from(Order.class);

		Path<Long> orderNumber = order.get(Order_.ORDER_NUMBER);
		Path<String> status = order.get(Order_.STATUS);
		Path<Date> requiredDate = order.get(Order_.REQUIRED_DATE);

		Predicate requiredDateSpecification = specification.toPredicate(order, query, builder());

		query.multiselect(orderNumber, status, requiredDate.as(Date.class));
		query.where(requiredDateSpecification);

		return entityManager.createQuery(query).getResultList();

	}
}
