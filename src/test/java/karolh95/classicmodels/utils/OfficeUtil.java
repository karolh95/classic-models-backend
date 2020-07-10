package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.model.Office;

public class OfficeUtil {

	public static Office office() {

		Office office = new Office();

		office.setOfficeCode("1");
		office.setPostalCode("postalCode");
		office.setTerritory("territory");

		office.setAddress(AddressUtil.address());

		return office;
	}

	public static DtoOffice dtoOffice() {

		DtoOffice dtoOffice = new DtoOffice();

		dtoOffice.setOfficeCode("2");
		dtoOffice.setPostalCode("new_postalCode");
		dtoOffice.setTerritory("new_territory");
		dtoOffice.setAddress(AddressUtil.dtoNewAddress());

		return dtoOffice;
	}

	public static List<Office> offices() {

		List<Office> offices = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			offices.add(office());
		}

		return offices;
	}

	public static void assertEquals(Office office, DtoOffice dtoOffice) {

		assertNotNull(office, "Office should not be null");
		assertNotNull(dtoOffice, "DTO office should not be null");

		Assertions.assertEquals(office.getOfficeCode(), dtoOffice.getOfficeCode(), "Office code should match");
		Assertions.assertEquals(office.getPostalCode(), dtoOffice.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(office.getTerritory(), dtoOffice.getTerritory(), "Territory should match");

		AddressUtil.assertEquals(office.getAddress(), dtoOffice.getAddress());
	}

	public static void assertUpdated(Office original, DtoOffice dtoOffice, Office office) {

		assertUpdatedWithoutAddress(original, dtoOffice, office);

		AddressUtil.assertEquals(office.getAddress(), dtoOffice.getAddress());
	}

	public static void assertUpdatedWithoutAddress(Office original, DtoOffice dtoOffice, Office office) {
		Assertions.assertEquals(original.getOfficeCode(), office.getOfficeCode(), "Office code should match");

		Assertions.assertEquals(office.getPostalCode(), dtoOffice.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(office.getTerritory(), dtoOffice.getTerritory(), "Territory should match");

	}
}