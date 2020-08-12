package karolh95.classicmodels.criteria.specification;

import java.math.BigDecimal;
import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetail_;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Product_;

public class ProductSpecification {

	public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {

		return (product, query, builder) -> builder.between(product.get(Product_.BUY_PRICE), min,
				max);
	}

	public static Specification<Product> priceNotBetween(BigDecimal min, BigDecimal max) {

		return (product, query, builder) -> builder
				.between(product.get(Product_.BUY_PRICE), min, max).not();
	}

	public static Specification<Product> priceLessThanMSRP(Join<Product, OrderDetail> orderDetail) {

		return (product, query, builder) -> builder
				.lessThan(orderDetail.get(OrderDetail_.PRICE_EACH), product.get(Product_.MSRP));
	}

	public static Specification<Product> hasProductCode(String productCode) {

		return (product, query, builder) -> builder.equal(product.get(Product_.PRODUCT_CODE),
				productCode);
	}
}
