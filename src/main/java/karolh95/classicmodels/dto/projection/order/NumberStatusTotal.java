package karolh95.classicmodels.dto.projection.order;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NumberStatusTotal {

	private Long orderNumber;
	private String status;
	private BigDecimal total;
}
