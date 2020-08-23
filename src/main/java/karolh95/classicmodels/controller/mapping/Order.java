package karolh95.classicmodels.controller.mapping;

import karolh95.classicmodels.controller.mapping.Mappings.BasicMapping;

public final class Order extends BasicMapping {

	public static final String PAYMENT_GENERATE = "payment/generate";
	public static final String PAYMENT_MAKE = "payment/make";

	public static final String REPORT = Mappings.ORDER + "/report";

	public static final class Report {

		public static final String SUMMARY = "summary";
		public static final String STATUS = "status";
		public static final String STATUS_DISTINCT = STATUS + "/distinct";
		public static final String TOTAL_GREATER_THAN = "total/greater/{total}";
		public static final String REQUIRED_BETWEEN = "required/between/{from}/{to}";
		public static final String REQUIRED_NOTBETWEEN = "required/notBetween/{from}/{to}";
		public static final String TOTAL = "total";
		public static final String PRODUCT_PRICE = "product/price";
		public static final String CUSTOMER_PRICE = "customer/price";
		public static final String BY_ORDERNUMBER = "orderNumber/{orderNumber}";
	}
}
