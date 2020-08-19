package karolh95.classicmodels.criteria;

import java.math.BigDecimal;
import java.util.List;
import karolh95.classicmodels.dto.projection.product.CodeNameDescription;
import karolh95.classicmodels.dto.projection.product.CodeNamePrice;
import karolh95.classicmodels.dto.projection.product.WithOrderNumberMsrpPrice;

public interface ProductRepositoryCustom {

	List<CodeNamePrice> findByPriceBetween(BigDecimal min, BigDecimal max);

	List<CodeNamePrice> findByPriceNotBetween(BigDecimal min, BigDecimal max);

	List<CodeNameDescription> findProductsWithDescription();

	List<WithOrderNumberMsrpPrice> getPriceDetailsbyProductCode(String productCode);
}
