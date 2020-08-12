package karolh95.classicmodels.criteria.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.ProductRepositoryCustom;
import karolh95.classicmodels.criteria.specification.ProductSpecification;
import karolh95.classicmodels.dto.projection.product.CodeNameDescription;
import karolh95.classicmodels.dto.projection.product.CodeNamePrice;
import karolh95.classicmodels.dto.projection.product.WithOrderNumberMsrpPrice;
import karolh95.classicmodels.model.OrderDetail;
import karolh95.classicmodels.model.OrderDetail_;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Product_;
import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.model.Productline_;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<CodeNamePrice> findByPriceBetween(BigDecimal min, BigDecimal max) {

		Specification<Product> priceBetween = ProductSpecification.priceBetween(min, max);
		return findBySpecification(priceBetween);
	}

	@Override
	public List<CodeNamePrice> findByPriceNotBetween(BigDecimal min, BigDecimal max) {

		Specification<Product> priceNotBetween = ProductSpecification.priceNotBetween(min, max);
		return findBySpecification(priceNotBetween);
	}

	private List<CodeNamePrice> findBySpecification(Specification<Product> specification) {

		CriteriaQuery<CodeNamePrice> query = builder().createQuery(CodeNamePrice.class);
		Root<Product> product = query.from(Product.class);

		Path<String> productCode = product.get(Product_.PRODUCT_CODE);
		Path<String> productName = product.get(Product_.PRODUCT_NAME);
		Path<BigDecimal> buyPrice = product.get(Product_.BUY_PRICE);

		query.multiselect(productCode, productName, buyPrice);
		query.where(specification.toPredicate(product, query, builder()));

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<CodeNameDescription> findProductsWithDescription() {

		CriteriaQuery<CodeNameDescription> query = builder().createQuery(CodeNameDescription.class);
		Root<Product> product = query.from(Product.class);

		Path<String> productCode = product.get(Product_.PRODUCT_CODE);
		Path<String> productName = product.get(Product_.PRODUCT_NAME);

		Join<Product, Productline> productline = product.join(Product_.PRODUCTLINE, JoinType.LEFT);
		Path<String> textDescription = productline.get(Productline_.TEXT_DESCRIPTION);

		query.multiselect(productCode, productName, textDescription);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithOrderNumberMsrpPrice> getPriceDetailsbyProductCode(String code) {

		CriteriaQuery<WithOrderNumberMsrpPrice> query =
				builder().createQuery(WithOrderNumberMsrpPrice.class);
		Root<Product> product = query.from(Product.class);

		Path<String> productName = product.get(Product_.PRODUCT_NAME);
		Path<String> productCode = product.get(Product_.PRODUCT_CODE);
		Path<BigDecimal> MSRP = product.get(Product_.MSRP);

		Join<Product, OrderDetail> orderDetail =
				product.join(Product_.ORDER_DETAILS, JoinType.LEFT);
		Path<BigDecimal> priceEach = orderDetail.get(OrderDetail_.PRICE_EACH);
		Path<Long> orderNumber = orderDetail.get(OrderDetail_.ORDER_NUMBER);

		Predicate priceLessThanMSRP = ProductSpecification.priceLessThanMSRP(orderDetail)
				.toPredicate(product, query, builder());

		Predicate hasProductCode =
				ProductSpecification.hasProductCode(code).toPredicate(product, query, builder());

		query.multiselect(productCode, productName, MSRP, orderNumber, priceEach);
		query.where(hasProductCode, priceLessThanMSRP);

		return entityManager.createQuery(query).getResultList();
	}


	private CriteriaBuilder builder() {
		return entityManager.getCriteriaBuilder();
	}
}
