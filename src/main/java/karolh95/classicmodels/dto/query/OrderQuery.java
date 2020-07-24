package karolh95.classicmodels.dto.query;

import java.sql.Date;

public class OrderQuery {

	public static interface Number {

		Long getOrderNumber();
	}

	public static interface Status {

		String getStatus();
	}

	public static interface Shipped {

		Date getShippedDate();
	}

	public static interface Required {

		Date getRequiredDate();
	}

	public static interface NumberStatus extends Number, Status {

	}

	public static interface NumberStatusShippedCustomerNumber extends NumberStatus, Shipped, CustomerQuery.Number {

	}

	public static interface NumberStatusRequired extends NumberStatus, Required {

	}
}