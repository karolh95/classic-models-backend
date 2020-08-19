package karolh95.classicmodels.dto.projection.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithDetails {

	private Long orderNumber;
	private Long customerNumber;
	private String productCode;
}
