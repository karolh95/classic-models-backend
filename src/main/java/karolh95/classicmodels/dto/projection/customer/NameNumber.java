package karolh95.classicmodels.dto.projection.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NameNumber {

	private String customerName;
	private Long customerNumber;
}
