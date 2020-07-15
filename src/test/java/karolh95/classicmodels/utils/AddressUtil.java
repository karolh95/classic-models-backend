package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.model.Address;

public final class AddressUtil {

	private static final String ADDRESS_LINE_1 = "addressLine1";
	private static final String ADDRESS_LINE_2 = "addressLine2";
	private static final String CITY = "city";
	private static final String COUNRTY = "country";
	private static final String PHONE = "phone";
	private static final String STATE = "state";

	private static final String NEW_ADDRESS_LINE_1 = "new_addressLine1";
	private static final String NEW_ADDRESS_LINE_2 = "new_addressLine2";
	private static final String NEW_CITY = "new_city";
	private static final String NEW_COUNTRY = "new_country";
	private static final String NEW_PHONE = "new_phone";
	private static final String NEW_STATE = "new_state";

	private AddressUtil() {
	}

	public static Address address() {

		Address address = new Address();

		address.setAddressLine1(ADDRESS_LINE_1);
		address.setAddressLine2(ADDRESS_LINE_2);
		address.setCity(CITY);
		address.setCountry(COUNRTY);
		address.setPhone(PHONE);
		address.setState(STATE);

		return address;
	}

	public static DtoAddress dtoAddress() {

		DtoAddress address = dtoNewAddress();

		address.setAddressLine1(ADDRESS_LINE_1);
		address.setAddressLine2(ADDRESS_LINE_2);
		address.setCity(CITY);
		address.setCountry(COUNRTY);
		address.setPhone(PHONE);
		address.setState(STATE);

		return address;
	}

	public static DtoAddress dtoNewAddress() {

		DtoAddress dtoAddress = new DtoAddress();

		dtoAddress.setAddressLine1(NEW_ADDRESS_LINE_1);
		dtoAddress.setAddressLine2(NEW_ADDRESS_LINE_2);
		dtoAddress.setCity(NEW_CITY);
		dtoAddress.setCountry(NEW_COUNTRY);
		dtoAddress.setPhone(NEW_PHONE);
		dtoAddress.setState(NEW_STATE);

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