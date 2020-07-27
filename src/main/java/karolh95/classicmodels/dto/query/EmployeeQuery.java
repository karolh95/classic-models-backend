package karolh95.classicmodels.dto.query;

public class EmployeeQuery {

	public interface Name {
		String getLastName();

		String getFirstName();
	}

	public interface JobTitle {
		String getJobTitle();
	}

	public interface OfficeCode {
		String getOfficeCode();
	}

	public interface NameJobTitle extends Name, JobTitle {

	}

	public interface NameJobTitleOffice extends NameJobTitle, OfficeCode {

	}
}