package karolh95.classicmodels.dto.query;

import java.sql.Date;

public interface OrderSummary {

	Long getOrderNumber();

	Long getCustomerNumber();

	String getStatus();

	Date getShippedDate();
}