package karolh95.classicmodels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DTOOffice {
	
	private String officeCode;
	private DTOAddress address;
	private String postalCode;
	private String territory;
}