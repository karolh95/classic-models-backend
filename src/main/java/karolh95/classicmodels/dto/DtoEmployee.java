package karolh95.classicmodels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoEmployee {
	
	private Long employeeNumber;
	private String lastName;
	private String firstName;
	private String extension;
	private String email;
	private String jobTitle;
	private Long reportsTo;
	private String officeCode;
}