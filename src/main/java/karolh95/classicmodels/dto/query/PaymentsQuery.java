package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;
import java.sql.Date;

public class PaymentsQuery {

	public interface Check {
		String getCheckNumber();
	}

	public interface Customer {
		Long getCustomerNumber();
	}

	public interface Payment {
		Date getPaymentDate();
	}

	public interface Amount {
		BigDecimal getAmount();
	}


}
