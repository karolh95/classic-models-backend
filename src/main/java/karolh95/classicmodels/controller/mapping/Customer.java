package karolh95.classicmodels.controller.mapping;

import karolh95.classicmodels.controller.mapping.Mappings.BasicMapping;

public final class Customer extends BasicMapping {

	public static final String RAPORT = Mappings.CUSTOMER + "/raport";

	public static final class Raport {

		public static final String CONTACTS_ASC = "contacts/asc";
		public static final String CONTACTS_DESC = "contacts/desc";

		public static final String STATES_ALL = "states/all";
		public static final String STATES_FIRST_5 = "states/5";
		public static final String STATES_CITY = "states/city";
		public static final String STATES_BY_COUNTRY = "states/country/{country}";

		public static final String BY_COUNTRY_STATE = "country/{country}/state/{state}";
		public static final String BY_COUNTRY_STATE_CREDITLIMIT =
				BY_COUNTRY_STATE + "/creditLimit/{creditLimit}";

		public static final String BY_CREDITLIMIT_COUNTRIES =
				"creditLimit/{creditLimit}/countries/{country1}/{country2}";

		public static final String BY_PAGE = "page/{page}/size/{size}";

		public static final String CREDITLIMIT_NTH_LOWEST = "creditLimit/lowest/{n}";
		public static final String CREDITLIMIT_NTH_HIGHEST = "creditLimit/highest/{n}";

		public static final String SALESREP = "salesReps";

		public static final String WITH_ORDERS = "orders/true";
		public static final String WITHOUT_ORDERS = "orders/false";
	}
}
