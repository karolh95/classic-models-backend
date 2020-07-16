package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public interface OrderDetailSummary {

	Long getOrderNumber();

	short getOrderLineNumber();

	BigDecimal getSubtotal();
}