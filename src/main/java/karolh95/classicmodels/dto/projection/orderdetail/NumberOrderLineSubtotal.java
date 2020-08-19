package karolh95.classicmodels.dto.projection.orderdetail;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NumberOrderLineSubtotal {

	private Long orderNumber;
	private short orderLine;
	private BigDecimal subtotal;
}
