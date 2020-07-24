package karolh95.classicmodels.dto.query;

public class OfficeQuery {

	public static interface Code {
		String getOfficeCode();
	}

	public static interface CodeCityCountryPhone
			extends Code, AddressQuery.City, AddressQuery.Country, AddressQuery.Phone {

	}
}