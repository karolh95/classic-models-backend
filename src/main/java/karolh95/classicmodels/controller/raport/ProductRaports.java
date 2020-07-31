package karolh95.classicmodels.controller.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Product;
import karolh95.classicmodels.dto.query.ProductQuery;
import karolh95.classicmodels.dto.query.ProductQuery.WithOrderNumberMsrpPrice;
import karolh95.classicmodels.service.raport.ProductRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Product.RAPORT)
public class ProductRaports {

	private final ProductRaport raport;

	@GetMapping(Product.Raport.BY_PRICE_BETWEEN)
	public List<ProductQuery.CodeNameBuyPrice> findByPriceBetween(@PathVariable BigDecimal low,
			@PathVariable BigDecimal high) {

		return raport.findByPriceBetween(low, high);
	}

	@GetMapping(Product.Raport.BY_PRICE_NOT_BETWEEN)
	public List<ProductQuery.CodeNameBuyPrice> findByPriceNotBetween(@PathVariable BigDecimal low,
			@PathVariable BigDecimal high) {

		return raport.findByPriceNotBetween(low, high);
	}

	@GetMapping(Product.Raport.DESCRIPTION)
	public List<ProductQuery.CodeNameDescription> getProductsWithDescription() {

		return raport.getProductsWithDescription();
	}

	@GetMapping(Product.Raport.DETAILS_BY_PRODUCTCODE)
	public List<WithOrderNumberMsrpPrice> getProductsWithOrderNumberMsrpAndPrice(
			@PathVariable String productCode) {

		return raport.getProductsWithOrderNumberMsrpAndPrice(productCode);
	}
}