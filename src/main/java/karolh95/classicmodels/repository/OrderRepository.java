package karolh95.classicmodels.repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.OrderQuery;
import karolh95.classicmodels.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT orderNumber as orderNumber, status as status FROM orders "
			+ "ORDER BY FIELD(status, 'In Process', 'On Hold', 'Cancelled', 'Resolved', 'Disputed','Shipped')")
	List<OrderQuery.NumberStatus> findAllOrderByState();

	List<OrderQuery.NumberStatus> findBy();

	List<OrderQuery.NumberStatusShippedCustomerNumber> findByOrderNumberIn(
			Collection<Long> orderNumbers);

	List<OrderQuery.NumberStatusRequired> findByRequiredDateBetween(Date from, Date to);

	List<OrderQuery.NumberStatusRequired> findByRequiredDateBeforeOrRequiredDateAfter(Date from,
			Date to);

	@Query("SELECT o.orderNumber as orderNumber, o.status as status, SUM(od.quantityOrdered * od.priceEach) as total "
			+ "FROM orders o INNER JOIN o.orderDetails od GROUP BY orderNumber")
	List<OrderQuery.NumberStatusTotal> getOrdersWithTotal();

	@Query("SELECT o.orderNumber as orderNumber, o.orderDate as orderDate, od.orderLineNumber as orderLineNumber, od.quantityOrdered as quantityOrdered, od.priceEach as priceEach, p.productName as productName "
			+ "FROM orders o INNER JOIN o.orderDetails od INNER JOIN od.product p ORDER BY orderNumber, orderLineNumber")
	List<OrderQuery.WithProductAndPrice> getOrdersWithProductAndPrice();

	@Query("SELECT o.orderNumber as orderNumber, o.orderDate as orderDate, "
			+ "od.orderLineNumber as orderLineNumber, od.quantityOrdered as quantityOrdered, od.priceEach as priceEach, "
			+ "p.productName as productName, c.customerName as customerName "
			+ "FROM orders o INNER JOIN o.orderDetails od INNER JOIN od.product p INNER JOIN o.customer c "
			+ "ORDER BY orderNumber, orderLineNumber")

	List<OrderQuery.WithCustomerAndPrice> getOrdersWithCustomerAndPrice();

	List<OrderQuery.WithDetail> findAllByOrderNumber(Long orderNumber);
}
