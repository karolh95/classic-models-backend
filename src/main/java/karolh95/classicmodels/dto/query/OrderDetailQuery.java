package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class OrderDetailQuery {

	public interface Quantity {
		int getQuantityOrdered();
	}

	public interface PriceEach {
		BigDecimal getPriceEach();
	}

	public interface OrderLine {
		short getOrderLineNumber();
	}
}
