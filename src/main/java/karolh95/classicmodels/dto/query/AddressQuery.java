package karolh95.classicmodels.dto.query;

public class AddressQuery {

	public interface Phone {
		String getAddress_Phone();
	}

	public interface AddressLine1 {
		String getAddress_AddressLine1();
	}

	public interface AddressLine2 {
		String getAddress_AddressLine2();
	}

	public interface City {
		String getAddress_City();
	}

	public interface State {
		String getAddress_State();
	}

	public interface Country {
		String getAddress_Country();
	}


	public interface StateCity extends State, City {

	}
}
