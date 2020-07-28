package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class OrderDetailQuery {

	public interface Number {
		Long getOrderNumber();
	}

	public interface OrderLine {
		short getOrderLineNumber();
	}

	public interface Subtotal {
		BigDecimal getSubtotal();
	}

	public interface Quantity {

		int getQuantityOrdered();
	}

	public interface PriceEach {

		BigDecimal getPriceEach();
	}

	public interface NumberOrderLineSubtotal extends Number, OrderLine, Subtotal {

	}
}
