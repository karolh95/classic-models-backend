package karolh95.classicmodels.dto.query;

public class AddressQuery {

	public static interface State {
		String getAddress_State();
	}

	public static interface City {
		String getAddress_City();
	}

	public static interface Country {
		String getAddress_Country();
	}

	public static interface Phone {
		String getAddress_Phone();
	}

	public interface StateCity extends State, City {

	}
}