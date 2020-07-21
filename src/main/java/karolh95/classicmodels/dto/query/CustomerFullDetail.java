package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public interface CustomerFullDetail extends CustomerDetail {

	BigDecimal getCreditLimit();
}