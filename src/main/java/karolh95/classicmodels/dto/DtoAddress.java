package karolh95.classicmodels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter
@Setter
public class DtoAddress {

	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;

	public boolean isValid() {

		if (phone == null) {
			return false;
		}

		if (addressLine1 == null) {
			return false;
		}

		if (city == null) {
			return false;
		}

		if (country == null) {
			return false;
		}

		return true;
	}
}
