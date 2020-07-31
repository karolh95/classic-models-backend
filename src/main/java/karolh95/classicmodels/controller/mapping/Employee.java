package karolh95.classicmodels.controller.mapping;

import karolh95.classicmodels.controller.mapping.Mappings.BasicMapping;

public final class Employee extends BasicMapping {

	public static final String RAPORT = Mappings.EMPLOYEE + "/raport";

	public static final class Raport {

		public static final String SUMMARY = "summary";

		public static final String BY_JOB_OFFICE = "jobTitle/{jobTitle}/officeCode/{officeCode}";

		public static final String OFFICECODE_BETWEEN = "officeCode/{low}/{high}";

		public static final String LASTNAME = "lastName/{lastName}";

		public static final String CUSTOMER_PAYMENTS = "customer/payments";

		public static final String WITH_CUSTOMER = "customer/true";
		public static final String WITHOUT_CUSTOMER = "customer/false";

		public static final String WITH_REPORTSTO = "reportsTo";
	}
}
