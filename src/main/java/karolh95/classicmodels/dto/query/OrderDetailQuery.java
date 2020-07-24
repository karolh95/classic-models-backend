package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class OrderDetailQuery {

	public static interface Number {
		Long getOrderNumber();
	}

	public static interface OrderLine {
		short getOrderLineNumber();
	}

	public static interface Subtotal {
		BigDecimal getSubtotal();
	}

	public static interface NumberOrderLineSubtotal extends Number, OrderLine, Subtotal {

	}
}