package karolh95.classicmodels.dto.query;

import java.sql.Date;

public class OrderQuery {

	public interface Number {

		Long getOrderNumber();
	}

	public interface Status {

		String getStatus();
	}

	public interface Shipped {

		Date getShippedDate();
	}

	public interface Required {

		Date getRequiredDate();
	}

	public interface NumberStatus extends Number, Status {

	}

	public interface NumberStatusShippedCustomerNumber extends NumberStatus, Shipped, CustomerQuery.Number {

	}

	public interface NumberStatusRequired extends NumberStatus, Required {

	}
}