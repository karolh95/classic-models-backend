package karolh95.classicmodels.dto.projection.customer;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class NameCountryState {

	private String customerName;
	private String country;
	private String state;
}
