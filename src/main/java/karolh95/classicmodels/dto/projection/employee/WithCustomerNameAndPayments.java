package karolh95.classicmodels.dto.projection.employee;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithCustomerNameAndPayments {

	private String name;
	private String customerName;
	private String checkNumber;
	private BigDecimal amount;
}
