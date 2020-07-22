package karolh95.classicmodels.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.ProductDetail;
import karolh95.classicmodels.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	List<ProductDetail> findByBuyPriceBetween(BigDecimal low, BigDecimal high);

	List<ProductDetail> findByBuyPriceLessThanOrBuyPriceGreaterThan(BigDecimal low, BigDecimal high);
}