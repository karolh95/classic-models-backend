package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderQuery {

	public interface Number {

		Long getOrderNumber();
	}

	public interface CustomerNumber {

		Long getCustomerNumber();
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

	public interface Order {

		Date getOrderDate();
	}

	public interface NumberStatus extends Number, Status {

	}

	public interface NumberStatusShippedCustomerNumber
			extends NumberStatus, Shipped, CustomerQuery.Number {

	}

	public interface NumberStatusRequired extends NumberStatus, Required {

	}

	public interface NumberStatusTotal extends NumberStatus {

		BigDecimal getTotal();
	}

	public interface WithProductAndPrice extends Number, Order, ProductQuery.Name,
			OrderDetailQuery.OrderLine, OrderDetailQuery.Quantity, OrderDetailQuery.PriceEach {

	}

	public interface WithCustomerAndPrice extends WithProductAndPrice, CustomerQuery.Name {

	}

	public interface WithDetail extends Number, CustomerNumber {

		String getOrderDetails_ProductCode();
	}
}
