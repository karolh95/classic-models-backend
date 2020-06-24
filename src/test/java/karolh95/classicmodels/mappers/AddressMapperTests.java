package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.mapper.AddressMapper;
import karolh95.classicmodels.mapper.AddressMapperImpl;
import karolh95.classicmodels.model.Address;

public class AddressMapperTests {

	private AddressMapper mapper = new AddressMapperImpl();

	@Test
	@DisplayName("Should map Address to Dto")
	public void mapAddressToDto() {

		Address address = address();

		DtoAddress dtoAddress = this.mapper.addressToDto(address);

		assertAddressesEqualss(address, dtoAddress);
	}

	private void assertAddressesEqualss(Address address, DtoAddress dtoAddress) {

		assertEquals(address.getPhone(), dtoAddress.getPhone(), "Phone should match");
		assertEquals(address.getAddressLine1(), dtoAddress.getAddressLine1(), "Address line 1 should match");
		assertEquals(address.getAddressLine2(), dtoAddress.getAddressLine2(), "Address line 2 should match");
		assertEquals(address.getCity(), dtoAddress.getCity(), "City should match");
		assertEquals(address.getState(), dtoAddress.getState(), "State should match");
		assertEquals(address.getCountry(), dtoAddress.getCountry(), "Country should match");
	}

	private Address address() {

		Address address = new Address();

		address.setAddressLine1("addressLine1");
		address.setAddressLine2("addressLine2");
		address.setCity("city");
		address.setCountry("country");
		address.setPhone("phone");
		address.setState("state");

		return address;
	}
}