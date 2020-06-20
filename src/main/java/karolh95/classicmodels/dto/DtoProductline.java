package karolh95.classicmodels.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoProductline {

	@NotBlank
	@Size(max = 50)
	private String productline;

	@NotBlank
	@Size(max = 4000)
	private String textDescription;

	private String htmlDescription;
	
	private byte[] image;
}
