package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.model.Office;

public final class OfficeUtil {

	private static final int OFFICE_CODE_MIN = 1;
	private static final String POSTAL_CODE = "postalCode";
	private static final String TERRITORY = "territory";

	private static final int NEW_OFFICE_CODE = 2;
	private static final String NEW_POSTAL_CODE = "new_postalCode";
	private static final String NEW_TERRITORY = "new_territory";

	private static final int OFFICE_CODE_MAX = 6;

	private OfficeUtil() {

	}

	public static Office office() {

		return office(OFFICE_CODE_MIN);
	}

	private static Office office(int officeCode) {

		Office office = new Office();

		office.setOfficeCode(String.valueOf(officeCode));
		office.setPostalCode(POSTAL_CODE);
		office.setTerritory(TERRITORY);

		office.setAddress(AddressUtil.address());

		return office;
	}

	public static DtoOffice dtoOffice() {

		DtoOffice dtoOffice = new DtoOffice();

		dtoOffice.setOfficeCode(String.valueOf(NEW_OFFICE_CODE));
		dtoOffice.setPostalCode(NEW_POSTAL_CODE);
		dtoOffice.setTerritory(NEW_TERRITORY);
		dtoOffice.setAddress(AddressUtil.dtoNewAddress());

		return dtoOffice;
	}

	public static List<Office> offices() {

		List<Office> offices = new ArrayList<>();

		for (int officeCode = OFFICE_CODE_MIN; officeCode < OFFICE_CODE_MAX; officeCode++) {
			offices.add(office(officeCode));
		}

		return offices;
	}

	public static void assertEquals(Office office, DtoOffice dtoOffice) {

		assertNotNull(office, "Office should not be null");
		assertNotNull(dtoOffice, "DTO office should not be null");

		assertOfficesCodesEquals(office.getOfficeCode(), dtoOffice.getOfficeCode());
		assertModifiableFieldsEquals(office, dtoOffice);

		AddressUtil.assertEquals(office.getAddress(), dtoOffice.getAddress());
	}

	public static void assertUpdated(Office original, DtoOffice dtoOffice, Office office) {

		assertUpdatedWithoutAddress(original, dtoOffice, office);
		AddressUtil.assertEquals(office.getAddress(), dtoOffice.getAddress());
	}

	public static void assertUpdatedWithoutAddress(Office original, DtoOffice dtoOffice, Office office) {

		assertOfficesCodesEquals(original.getOfficeCode(), office.getOfficeCode());
		assertModifiableFieldsEquals(office, dtoOffice);
	}

	private static void assertOfficesCodesEquals(String expected, String actual) {

		Assertions.assertEquals(expected, actual, "Office code sshould match");
	}

	private static void assertModifiableFieldsEquals(Office office, DtoOffice dtoOffice) {

		Assertions.assertEquals(office.getPostalCode(), dtoOffice.getPostalCode(), "Postal code should match");
		Assertions.assertEquals(office.getTerritory(), dtoOffice.getTerritory(), "Territory should match");
	}
}