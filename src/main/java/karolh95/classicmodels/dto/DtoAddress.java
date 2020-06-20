package karolh95.classicmodels.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoAddress {

	@NotBlank
	@Size(max = 50)
	private String phone;

	@NotBlank
	@Size(max = 50)
	private String addressLine1;

	@Size(max = 50)
	private String addressLine2;

	@NotBlank
	@Size(max = 50)
	private String city;

	@Size(max = 50)
	private String state;

	@NotBlank
	@Size(max = 50)
	private String country;

}
