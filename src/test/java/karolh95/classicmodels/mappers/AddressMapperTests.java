package karolh95.classicmodels.mappers;

import static karolh95.classicmodels.utils.AddressUtil.address;
import static karolh95.classicmodels.utils.AddressUtil.dtoAddress;
import static karolh95.classicmodels.utils.AddressUtil.assertEquals;
import static karolh95.classicmodels.utils.AddressUtil.assertUpdated;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.mapper.AddressMapper;
import karolh95.classicmodels.mapper.AddressMapperImpl;
import karolh95.classicmodels.model.Address;

class AddressMapperTests {

	AddressMapper mapper = new AddressMapperImpl();

	Address address;
	DtoAddress dtoAddress;

	@Nested
	@DisplayName("addressToDto test")
	class AddressToDtoTest {

		@BeforeEach
		void init() {
			address = address();
		}

		@Test
		@DisplayName("Should not map null Address")
		void mapNullAddressToDto() {

			address = null;

			dtoAddress = mapper.addressToDto(null);

			assertNull(dtoAddress, "Dto address should be null");
		}

		@Test
		@DisplayName("Should map Address to Dto")
		void mapAddressToDto() {

			dtoAddress = mapper.addressToDto(address);

			assertEquals(address, dtoAddress);
		}
	}

	@Nested
	@DisplayName("updateFromDto test")
	class UpdateFromDtoTest {

		final Address original = address();

		@BeforeEach
		void init() {
			dtoAddress = dtoAddress();
		}

		@Test
		@DisplayName("Should not update Address when dto is null")
		void updateAddressFromNullDto() {

			dtoAddress = null;

			mapper.updateFromDto(dtoAddress, address);

			assertEquals(original, address);
		}

		@Test
		@DisplayName("Should update Address from dto")
		void updateAddressFromDto() {

			mapper.updateFromDto(dtoAddress, address);

			assertUpdated(original, dtoAddress, address);
		}

	}

}