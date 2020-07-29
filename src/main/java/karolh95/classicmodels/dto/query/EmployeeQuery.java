package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

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

	public interface EmployeeNumber {
		Long getEmployeeNumber();
	}

	public interface NameJobTitle extends Name, JobTitle {

	}

	public interface NameJobTitleOffice extends NameJobTitle, OfficeCode {

	}

	public interface WithCustomerNameAndPayments extends Name {

		String getCustomers_CustomerName();

		String getCustomers_Payments_CheckNumber();

		BigDecimal getCustomers_Payments_Amount();
	}

	public interface WithCustomerNumber extends EmployeeNumber {

		Long getCustomers_CustomerNumber();
	}

	public interface WithReportsTo extends Name, ReportsTo {

	}

	private interface ReportsTo {
		String getEmployee_LastName();

		String getEmployee_FirstName();
	}
}
