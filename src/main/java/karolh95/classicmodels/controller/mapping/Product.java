package karolh95.classicmodels.controller.mapping;

import karolh95.classicmodels.controller.mapping.Mappings.BasicMapping;

public final class Product extends BasicMapping {

	public static final String REPORT = Mappings.PRODUCT + "/report";

	public static final class Report {

		public static final String BY_PRICE_BETWEEN = "price/between/{low}/{high}";
		public static final String BY_PRICE_NOT_BETWEEN = "price/notBetween/{low}/{high}";

		public static final String DESCRIPTION = "description";

		public static final String DETAILS_BY_PRODUCTCODE = "details/productCode/{productCode}";
	}
}
