package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class OrderDetailQuery {

	public interface Number {
		Long getOrderNumber();
	}

	public interface ProductCode {
		String getProductCode();
	}

	public interface Quantity {
		int getQuantityOrdered();
	}

	public interface PriceEach {
		BigDecimal getPriceEach();
	}

	public interface OrderLine {
		short getOrderLineNumber();
	}

	public interface NumberOrderLineSubtotal extends Number, OrderLine {
		BigDecimal getSubtotal();
	}
}
