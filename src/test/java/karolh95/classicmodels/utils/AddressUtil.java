package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.model.Address;

public class AddressUtil {

	public static Address address() {

		Address address = new Address();

		address.setAddressLine1("addressLine1");
		address.setAddressLine2("addressLine2");
		address.setCity("city");
		address.setCountry("country");
		address.setPhone("phone");
		address.setState("state");

		return address;
	}

	public static DtoAddress dtoAddress() {

		DtoAddress dtoAddress = new DtoAddress();

		dtoAddress.setAddressLine1("new_addressLine1");
		dtoAddress.setAddressLine2("new_addressLine2");
		dtoAddress.setCity("new_city");
		dtoAddress.setCountry("new_country");
		dtoAddress.setPhone("new_phone");
		dtoAddress.setState("new_state");

		return dtoAddress;
	}

	public static void assertEquals(Address address, DtoAddress dtoAddress) {

		assertNotNull(address, "Address should not be null");
		assertNotNull(dtoAddress, "DTO address should not be null");

		Assertions.assertEquals(address.getAddressLine1(), dtoAddress.getAddressLine1(), "Address line 1 should match");
		Assertions.assertEquals(address.getAddressLine2(), dtoAddress.getAddressLine2(), "Address line 2 should match");
		Assertions.assertEquals(address.getCity(), dtoAddress.getCity(), "City should match");
		Assertions.assertEquals(address.getCountry(), dtoAddress.getCountry(), "Country should match");
		Assertions.assertEquals(address.getState(), dtoAddress.getState(), "State should match");
		Assertions.assertEquals(address.getPhone(), dtoAddress.getPhone(), "Phone should match");
	}

	public static void assertUpdated(Address original, DtoAddress dtoAddress, Address address) {

		assertEquals(address, dtoAddress);
	}
}