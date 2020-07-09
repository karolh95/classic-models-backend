package karolh95.classicmodels.mappers;

import static karolh95.classicmodels.utils.OfficeUtil.assertEquals;
import static karolh95.classicmodels.utils.OfficeUtil.assertUpdated;
import static karolh95.classicmodels.utils.OfficeUtil.assertUpdatedWithoutAddress;
import static karolh95.classicmodels.utils.OfficeUtil.office;
import static karolh95.classicmodels.utils.OfficeUtil.offices;
import static karolh95.classicmodels.utils.OfficeUtil.dtoOffice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.mapper.AddressMapper;
import karolh95.classicmodels.mapper.AddressMapperImpl;
import karolh95.classicmodels.mapper.OfficeMapper;
import karolh95.classicmodels.mapper.OfficeMapperImpl;
import karolh95.classicmodels.model.Office;

class OfficeMapperTests {

	@Spy
	AddressMapper addressMapper = new AddressMapperImpl();

	@InjectMocks
	OfficeMapper mapper = new OfficeMapperImpl();

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	@DisplayName("officeToDto test")
	class OfficeToDtoTest {

		@Test
		@DisplayName("Should not map null Office object to dto")
		void mapNullOfficeToDto() {

			Office office = null;

			DtoOffice dtoOffice = mapper.officeToDto(office);

			assertNull(dtoOffice, "Dto Office should be null");
		}

		@Test
		@DisplayName("Should map Office to Dto")
		void mapOfficeToDto() {

			Office office = office();
			DtoOffice dtoOffice = mapper.officeToDto(office);

			assertEquals(office, dtoOffice);
		}
	}

	@Nested
	@DisplayName("officesToDtos test")
	class OfficesToDtosTest {

		List<Office> offices;
		List<DtoOffice> dtoOffices;

		@BeforeEach
		void init() {
			offices = offices();
		}

		@Test
		@DisplayName("Should not map null offices to dtos")
		void mapNullOfficesToDtos() {

			offices = null;

			dtoOffices = mapper.officesToDtos(offices);

			assertNull(dtoOffices, "Dto offices should be null");
		}

		@Test
		@DisplayName("Should map empty offices to dtos")
		void mapEmptyOfficesToDtos() {

			offices.clear();

			dtoOffices = mapper.officesToDtos(offices);

			assertTrue(dtoOffices.isEmpty(), "Dto offices should be empty");
		}

		@Test
		@DisplayName("Should map offices to dtos")
		void mapOfficesToDtos() {

			dtoOffices = mapper.officesToDtos(offices);

			for (int i = 0; i < offices.size(); i++) {

				Office office = offices.get(i);
				DtoOffice dtoOffice = dtoOffices.get(i);

				assertEquals(office, dtoOffice);
			}

		}
	}

	@Nested
	@DisplayName("updateFromDto test")
	class UpdateFromDtoTest {

		final Office original = office();
		Office office;
		DtoOffice dtoOffice;

		@BeforeEach
		void init() {
			office = office();
			dtoOffice = dtoOffice();
		}

		@Test
		@DisplayName("Should not update office from null dto")
		void updateFromNullDto() {

			dtoOffice = null;

			mapper.updateFromDto(dtoOffice, office);

			assertEquals(original, office, "Should not update office");
		}

		@Test
		@DisplayName("Should update office from dto without address")
		void updateOfficeFromDtoWithoutAddress() {

			dtoOffice.setAddress(null);

			mapper.updateFromDto(dtoOffice, office);

			assertUpdatedWithoutAddress(original, dtoOffice, office);
		}

		@Test
		@DisplayName("Should update office without address from dto")
		void updateOfficeWithoutAddressToDto() {

			office.setAddress(null);

			mapper.updateFromDto(dtoOffice, office);

			assertUpdated(original, dtoOffice, office);
		}

		@Test
		@DisplayName("Should update office from dto")
		void updateOfficeFromDto() {

			mapper.updateFromDto(dtoOffice, office);

			assertUpdated(original, dtoOffice, office);
		}
	}
}