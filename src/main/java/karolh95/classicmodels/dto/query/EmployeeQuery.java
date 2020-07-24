package karolh95.classicmodels.dto.query;

public class EmployeeQuery {

	public static interface Name {
		String getLastName();

		String getFirstName();
	}

	public static interface JobTitle {
		String getJobTitle();
	}

	public static interface OfficeCode {
		String getOfficeCode();
	}

	public static interface NameJobTitle extends Name, JobTitle {

	}

	public static interface NameJobTitleOffice extends NameJobTitle, OfficeCode {

	}
}