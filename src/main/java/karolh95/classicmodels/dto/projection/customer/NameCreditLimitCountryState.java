package karolh95.classicmodels.dto.projection.customer;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NameCreditLimitCountryState {

	private String customerName;
	private BigDecimal creditLimit;
	private String country;
	private String state;

}
