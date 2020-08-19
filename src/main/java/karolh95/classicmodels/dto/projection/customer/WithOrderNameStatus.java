package karolh95.classicmodels.dto.projection.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithOrderNameStatus {

	private Long customerNumber;
	private String customerName;
	private Long orderNumber;
	private String status;
}
