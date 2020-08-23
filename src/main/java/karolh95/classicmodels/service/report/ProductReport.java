package karolh95.classicmodels.service.report;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.projection.product.CodeNameDescription;
import karolh95.classicmodels.dto.projection.product.CodeNamePrice;
import karolh95.classicmodels.dto.projection.product.WithOrderNumberMsrpPrice;
import karolh95.classicmodels.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReport {

	private final ProductRepository repository;

	public List<CodeNamePrice> findByPriceBetween(BigDecimal min, BigDecimal max) {

		return repository.findByPriceBetween(min, max);
	}

	public List<CodeNamePrice> findByPriceNotBetween(BigDecimal min, BigDecimal max) {

		return repository.findByPriceNotBetween(min, max);
	}

	public List<CodeNameDescription> getProductsWithDescription() {

		return repository.findProductsWithDescription();
	}

	public List<WithOrderNumberMsrpPrice> getProductsWithOrderNumberMsrpAndPrice(String code) {

		return repository.getPriceDetailsbyProductCode(code);
	}
}
