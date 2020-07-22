package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public interface ProductDetail {

	String getProductCode();

	String getProductName();

	BigDecimal getBuyPrice();
}