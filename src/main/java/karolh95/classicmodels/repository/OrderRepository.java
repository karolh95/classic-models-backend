package karolh95.classicmodels.repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.OrderStatus;
import karolh95.classicmodels.dto.query.OrderSummary;
import karolh95.classicmodels.dto.query.RequiredOrderStatus;
import karolh95.classicmodels.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT orderNumber as orderNumber, status as status "
			+ "FROM orders ORDER BY FIELD(status, 'In Process', 'On Hold', 'Cancelled', 'Resolved', 'Disputed','Shipped')")
	List<OrderStatus> findAllOrderByState();

	List<OrderStatus> findBy();

	List<OrderSummary> findByOrderNumberIn(Collection<Long> orderNumbers);

	List<RequiredOrderStatus> findByRequiredDateBetween(Date from, Date to);

	List<RequiredOrderStatus> findByRequiredDateBeforeOrRequiredDateAfter(Date from, Date to);
}