package karolh95.classicmodels.dto.query;

public class OfficeQuery {

	public interface Code {
		String getOfficeCode();
	}

	public interface CodeCityCountryPhone extends Code, AddressQuery.City, AddressQuery.Country, AddressQuery.Phone {

	}
}