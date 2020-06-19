package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoProduct {
	
	private String productCode;
	private String productName;
	private String productScale;
	private String productVendor;
	private String productDescription;
	private short quantityInStock;
	private BigDecimal MSRP;
	private BigDecimal buyPrice;
	private String productline;

	public boolean isValid() {

		if (productCode == null) {
			return false;
		}

		if (productline == null) {
			return false;
		}

		if (productName == null) {
			return false;
		}

		if (productScale == null) {
			return false;
		}

		if (productVendor == null) {
			return false;
		}

		if (productDescription == null) {
			return false;
		}

		if (buyPrice == null) {
			return false;
		}

		if (MSRP == null) {
			return false;
		}

		return true;
	}

}