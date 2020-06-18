package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetailPK;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

	List<OrderDetail> findByOrderDetailPKOrderNumber(Long orderNumber);

}