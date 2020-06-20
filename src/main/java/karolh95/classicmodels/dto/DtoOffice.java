package karolh95.classicmodels.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoOffice {

	@NotBlank
	@Size(max = 10)
	private String officeCode;

	@Valid
	@NotNull
	private DtoAddress address;

	@NotBlank
	@Size(max = 15)
	private String postalCode;

	@NotBlank
	@Size(max = 10)
	private String territory;

}