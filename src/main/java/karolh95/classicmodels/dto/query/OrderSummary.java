package karolh95.classicmodels.dto.query;

import java.sql.Date;

public interface OrderSummary extends CustomerQuery.Number {

	Long getOrderNumber();

	String getStatus();

	Date getShippedDate();
}