package karolh95.classicmodels.dto.query;

import java.sql.Date;

public interface RequiredOrderStatus {

	Long getOrderNumber();

	Date getRequiredDate();

	String getStatus();
}