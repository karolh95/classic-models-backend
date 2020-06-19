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

	public boolean isValid() {

		if (employeeNumber == null) {
			return false;
		}

		if (lastName == null) {
			return false;
		}

		if (firstName == null) {
			return false;
		}

		if (extension == null) {
			return false;
		}

		if (email == null) {
			return false;
		}

		if (jobTitle == null) {
			return false;
		}

		if (officeCode == null) {
			return false;
		}

		return true;
	}
}