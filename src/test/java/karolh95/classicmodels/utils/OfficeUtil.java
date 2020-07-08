package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	public static void assertEquals(Office office, DtoOffice dtoOffice) {

		assertNotNull(office, "Office should not be null");
		assertNotNull(dtoOffice, "DTO office should not be null");

		Assertions.assertEquals(office.getOfficeCode(), dtoOffice.getOfficeCode(), "Office code should match");
		Assertions.assertEquals(office.getPostalCode(), dtoOffice.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(office.getTerritory(), dtoOffice.getTerritory(), "Territory should match");

		AddressUtil.assertEquals(office.getAddress(), dtoOffice.getAddress());
	}
}