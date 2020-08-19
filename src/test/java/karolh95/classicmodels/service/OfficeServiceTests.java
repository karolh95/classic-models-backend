package karolh95.classicmodels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.mapper.OfficeMapper;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.repository.OfficeRepository;

@SpringBootTest
@Disabled
public class OfficeServiceTests {

	@MockBean
	private OfficeRepository repository;

	@Autowired
	private OfficeMapper mapper;

	@Autowired
	private OfficeService service;

	@Test
	@DisplayName("Should create new Office")
	public void createOffice() {

		when(repository.getOne(anyString())).thenThrow(new EntityNotFoundException());
		when(repository.save(any(Office.class))).thenReturn(office());

		DtoOffice office = dtoOffice();
		DtoOffice response = service.saveOffice(office);

		assertEquals(office, response, "Office and response should match");
	}

	@Test
	@DisplayName("Should update Office")
	public void updateOffice() {

		when(repository.getOne(anyString())).thenReturn(office());
		when(repository.save(any(Office.class))).thenReturn(modifiedOffice());

		DtoOffice office = modifiedDtoOffice();
		DtoOffice response = service.saveOffice(office);

		assertEquals(office, response, "Office and response should match");
	}

	private Office office() {

		Office office = new Office();

		office.setOfficeCode("1");
		office.setAddress(address());
		office.setPostalCode("postalCode");
		office.setTerritory("territory");

		return office;
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

	private DtoOffice dtoOffice() {

		return mapper.officeToDto(office());
	}

	private Office modifiedOffice() {

		Office office = office();

		office.setPostalCode("newPostalCode");
		office.setTerritory("newTerritory");
		office.setAddress(modifiedAddress());

		return office;
	}

	private Address modifiedAddress() {

		Address address = address();

		address.setAddressLine1("newAddressLine1");
		address.setAddressLine2("newAddressLine2");
		address.setCity("newCity");
		address.setCountry("newCountry");
		address.setPhone("newPhone");
		address.setState("newState");

		return address;
	}

	private DtoOffice modifiedDtoOffice() {

		return mapper.officeToDto(modifiedOffice());
	}

}
