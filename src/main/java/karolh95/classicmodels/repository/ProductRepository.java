package karolh95.classicmodels.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.ProductQuery;
import karolh95.classicmodels.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	List<ProductQuery.CodeNameBuyPrice> findByBuyPriceBetween(BigDecimal low, BigDecimal high);

	List<ProductQuery.CodeNameBuyPrice> findByBuyPriceLessThanOrBuyPriceGreaterThan(BigDecimal low,
			BigDecimal high);

	@Query("SELECT p.productCode as productCode, p.productName as productName, pl.textDescription as textDescription "
			+ "FROM products p INNER JOIN p.productline pl ")
	List<ProductQuery.CodeNameDescription> getProductsWithDescription();

	@Query("SELECT p.productName as productName, p.MSRP as MSRP, od.orderNumber as orderNumber, od.priceEach as priceEach "
			+ "FROM products p INNER JOIN p.orderDetails od ON p.MSRP > od.priceEach WHERE p.productCode = :code")
	List<ProductQuery.WithOrderNumberMsrpPrice> getProductsWithOrderNumberMsrpAndPrice(
			@Param("code") String code);
}
