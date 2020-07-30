package karolh95.classicmodels.service.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.query.ProductQuery;
import karolh95.classicmodels.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductRaport {

	private final ProductRepository repository;

	public List<ProductQuery.CodeNameBuyPrice> findByPriceBetween(BigDecimal low, BigDecimal high) {

		return repository.findByBuyPriceBetween(low, high);
	}

	public List<ProductQuery.CodeNameBuyPrice> findByPriceNotBetween(BigDecimal low,
			BigDecimal high) {

		return repository.findByBuyPriceLessThanOrBuyPriceGreaterThan(low, high);
	}

	public List<ProductQuery.CodeNameDescription> getProductsWithDescription() {

		return repository.getProductsWithDescription();
	}

	public List<ProductQuery.WithOrderNumberMsrpPrice> getProductsWithOrderNumberMsrpAndPrice(
			String code) {

		return repository.getProductsWithOrderNumberMsrpAndPrice(code);
	}
}
