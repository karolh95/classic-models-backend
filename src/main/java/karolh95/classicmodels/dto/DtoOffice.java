package karolh95.classicmodels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoOffice {
	
	private String officeCode;
	private DtoAddress address;
	private String postalCode;
	private String territory;

	public boolean isValid() {

		if (officeCode == null) {
			return false;
		}

		if (postalCode == null) {
			return false;
		}

		if (territory == null) {
			return false;
		}

		if (address == null || !address.isValid()) {
			return false;
		}

		return true;
	}
}