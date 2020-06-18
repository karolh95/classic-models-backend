package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.mapper.OfficeMapper;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.repository.OfficeRepository;

@SpringBootTest
public class OfficeMapperTests {

	@Autowired
	private OfficeMapper mapper;

	@Autowired
	private OfficeRepository repository;

	@Test
	@DisplayName("Should map Office to Dto")
	public void mapOfficeToDto() {

		Optional<Office> optional = this.repository.findById("1");

		assertTrue(optional.isPresent(), "Office should exist");

		Office office = optional.get();
		Address address = office.getAddress();
		DtoOffice dto = this.mapper.officeToDto(office);
		DtoAddress dtoAddress = dto.getAddress();

		assertEquals(office.getOfficeCode(), dto.getOfficeCode(), "Office code should match");
		assertEquals(office.getPostalCode(), dto.getPostalCode(), "Postal code should match");
		assertEquals(office.getTerritory(), dto.getTerritory(), "Territory should match");

		assertEquals(address.getAddressLine1(), dtoAddress.getAddressLine1(), "Address line 1 should match");
		assertEquals(address.getAddressLine2(), dtoAddress.getAddressLine2(), "Address line 2 should match");
		assertEquals(address.getCity(), dtoAddress.getCity(), "City should match");
		assertEquals(address.getCountry(), dtoAddress.getCountry(), "Country should match");
		assertEquals(address.getPhone(), dtoAddress.getPhone(), "Phone should match");
		assertEquals(address.getState(), dtoAddress.getState(), "State should match");
	}
}