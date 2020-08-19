package karolh95.classicmodels.dto.projection.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithOrderNumberMsrpPrice {

	private String productCode;
	private String productName;
	private BigDecimal MSRP;
	private Long orderNumber;
	private BigDecimal priceEach;
}
