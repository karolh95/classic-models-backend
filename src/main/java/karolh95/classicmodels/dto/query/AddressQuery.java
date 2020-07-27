package karolh95.classicmodels.dto.query;

public class AddressQuery {

	public interface State {
		String getAddress_State();
	}

	public interface City {
		String getAddress_City();
	}

	public interface Country {
		String getAddress_Country();
	}

	public interface Phone {
		String getAddress_Phone();
	}

	public interface StateCity extends State, City {

	}
}