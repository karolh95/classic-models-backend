package karolh95.classicmodels.dto.projection.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NumberStatus {

	private Long orderNumber;
	private String status;
}
