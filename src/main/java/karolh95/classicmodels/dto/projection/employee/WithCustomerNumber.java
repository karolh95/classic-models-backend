package karolh95.classicmodels.dto.projection.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithCustomerNumber {

	private String name;
	private Long customerNumber;
}
