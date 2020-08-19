package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.OrderRepositoryCustom;
import karolh95.classicmodels.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
