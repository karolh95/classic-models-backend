package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoCustomer {
	private Long customerNumber;
	private String customerName;
	private String contactLastName;
	private String contactFirstName;
	private DtoAddress address;
	private String postalCode;
	private BigDecimal creditLimit;
	private Long salesRepEmployeeNumber;

	public boolean isValid() {

		if (customerNumber == null) {
			return false;
		}

		if (customerName == null) {
			return false;
		}

		if (contactLastName == null) {
			return false;
		}

		if (contactFirstName == null) {
			return false;
		}

		if (address == null || !address.isValid()) {
			return false;
		}

		return true;
	}
}