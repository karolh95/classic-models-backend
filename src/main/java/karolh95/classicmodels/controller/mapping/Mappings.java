package karolh95.classicmodels.controller.mapping;

public final class Mappings {

	private Mappings() {
	}

	public static final String CUSTOMER = "customer";
	public static final String EMPLOYEE = "employee";
	public static final String OFFICE = "office";
	public static final String ORDER = "order";
	public static final String PAYMENT = "payment";
	public static final String PRODUCT = "product";
	public static final String PRODUCTLINE = "productline";

	public static class BasicMapping {

		public static final String ALL = "all";
		public static final String GET = "get/{id}";
		public static final String SAVE = "save";
	}
}
