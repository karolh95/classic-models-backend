package karolh95.classicmodels.dto.projection.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NameSalesRepCountry {

	private String customerName;
	private Long salesRepEmployeeNumber;
	private String country;
}
