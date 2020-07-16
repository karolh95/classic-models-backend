package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.OrderDetailSummary;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

	List<OrderDetail> findByOrderDetailPKOrderNumber(Long orderNumber);

	@Query("SELECT order.orderNumber as orderNumber, orderLineNumber as orderLineNumber, quantityOrdered * priceEach AS subtotal "
			+ "FROM orderdetails ORDER BY subtotal DESC")
	List<OrderDetailSummary> findAllSummary();
}