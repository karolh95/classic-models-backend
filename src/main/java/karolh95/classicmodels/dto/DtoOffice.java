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
}