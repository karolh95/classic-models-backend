package karolh95.classicmodels.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoEmployee {

	@NotNull
	private Long employeeNumber;

	@NotBlank
	@Size(max = 50)
	private String lastName;
	
	@NotBlank
	@Size(max = 50)
	private String firstName;
	
	@NotBlank
	@Size(max = 10)
	private String extension;
	
	@NotBlank
	@Size(max = 100)
	@Email()
	private String email;
	
	@NotBlank
	@Size(max = 50)
	private String jobTitle;
	
	private Long reportsTo;
	
	@NotNull
	private String officeCode;


}