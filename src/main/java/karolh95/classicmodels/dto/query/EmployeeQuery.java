package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class EmployeeQuery {

	public interface Number {
		Long getEmployeeNumber();
	}

	public interface Name {
		String getLastName();

		String getFirstName();
	}

	public interface Extension {
		String getExtension();
	}

	public interface Email {
		String getEmail();
	}

	public interface Job {
		String getJobTitle();
	}

	public interface ReportsTo {
		Long getReportsTo();
	}

	public interface Office {
		String getOfficeCode();
	}

	public interface NameJob extends Name, Job {
	}

	public interface NameJobOffice extends NameJob, Office {
	}

	public interface WithCustomerNameAndPayments extends Name {
		String getCustomers_CustomerName();

		String getCustomers_Payments_CheckNumber();

		BigDecimal getCustomers_Payments_Amount();
	}

	public interface WithCustomerNumber extends Number {
		Long getCustomers_CustomerNumber();
	}

	public interface WithManager extends Name, Manager {
	}

	private interface Manager {
		String getEmployee_LastName();

		String getEmployee_FirstName();
	}
}
