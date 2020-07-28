package karolh95.classicmodels.controller.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.ProductQuery;
import karolh95.classicmodels.service.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("product/raports")
public class ProductRaports {

	private final ProductService service;

	@GetMapping("byPriceBetween/{low}/{high}")
	public List<ProductQuery.CodeNameBuyPrice> findByPriceBetween(@PathVariable BigDecimal low,
			@PathVariable BigDecimal high) {

		return service.findByPriceBetween(low, high);
	}

	@GetMapping("byPriceNotBetween/{low}/{high}")
	public List<ProductQuery.CodeNameBuyPrice> findByPriceNotBetween(@PathVariable BigDecimal low,
			@PathVariable BigDecimal high) {

		return service.findByPriceNotBetween(low, high);
	}
}
