package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class ProductQuery {

	public static interface Code {

		String getProductCode();
	}

	public static interface Name {

		String getProductName();
	}

	public static interface BuyPrice {

		BigDecimal getBuyPrice();
	}

	public static interface CodeNameBuyPrice extends Name, Code, BuyPrice {

	}
}