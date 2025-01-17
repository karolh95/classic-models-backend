package karolh95.classicmodels.controller.mapping;

import karolh95.classicmodels.controller.mapping.Mappings.BasicMapping;

public final class Office extends BasicMapping {

	public static final String REPORT = Mappings.OFFICE + "/report";

	public static final class Report {

		public static final String COUNTRIES_IN = "countries/in/{country1}/{country2}";
		public static final String COUNTRIES_NOT_IN = "countries/notIn/{country1}/{country2}";
	}
}
