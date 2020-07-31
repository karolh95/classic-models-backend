package karolh95.classicmodels.dto.query;

public class OfficeQuery extends AddressQuery {

	public interface Code {
		String getOfficeCode();
	}

	public interface PostalCode {
		String getPostalCode();
	}

	public interface Territory {
		String getTerritory();
	}

	public interface CodeCityCountryPhone extends Code, City, Country, Phone {

	}
}
