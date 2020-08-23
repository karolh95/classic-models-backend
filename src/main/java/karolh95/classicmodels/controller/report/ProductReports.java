package karolh95.classicmodels.controller.report;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Product;
import karolh95.classicmodels.dto.projection.product.CodeNameDescription;
import karolh95.classicmodels.dto.projection.product.CodeNamePrice;
import karolh95.classicmodels.dto.projection.product.WithOrderNumberMsrpPrice;
import karolh95.classicmodels.service.raport.ProductRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Product.REPORT)
public class ProductReports {

	private final ProductRaport raport;

	@GetMapping(Product.Report.BY_PRICE_BETWEEN)
	public List<CodeNamePrice> findByPriceBetween(@PathVariable BigDecimal low,
			@PathVariable BigDecimal high) {

		return raport.findByPriceBetween(low, high);
	}

	@GetMapping(Product.Report.BY_PRICE_NOT_BETWEEN)
	public List<CodeNamePrice> findByPriceNotBetween(@PathVariable BigDecimal low,
			@PathVariable BigDecimal high) {

		return raport.findByPriceNotBetween(low, high);
	}

	@GetMapping(Product.Report.DESCRIPTION)
	public List<CodeNameDescription> getProductsWithDescription() {

		return raport.getProductsWithDescription();
	}

	@GetMapping(Product.Report.DETAILS_BY_PRODUCTCODE)
	public List<WithOrderNumberMsrpPrice> getProductsWithOrderNumberMsrpAndPrice(
			@PathVariable String productCode) {

		return raport.getProductsWithOrderNumberMsrpAndPrice(productCode);
	}
}