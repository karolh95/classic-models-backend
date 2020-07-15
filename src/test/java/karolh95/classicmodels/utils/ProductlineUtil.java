package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.model.Productline;

public final class ProductlineUtil {

	private static final int PRODUCTLINE_MIN = 1;
	private static final String TEXT_DESCRIPTION = "textDescription";
	private static final String HTML_DESCRIPTION = "htmlDescription";
	private static final byte[] IMAGE = new byte[1];

	private static final int NEW_PRODUCTLINE = 2;
	private static final String NEW_TEXT_DESCRIPTION = "new_textDescription";
	private static final String NEW_HTML_DESCRIPTION = "new_htmlDescription";
	private static final byte[] NEW_IMAGE = new byte[2];

	private static final int PRODUCTLINE_MAX = 6;

	private ProductlineUtil() {

	}

	public static Productline productline() {

		return productline(PRODUCTLINE_MIN);
	}

	private static Productline productline(int productlineCode) {

		Productline productline = new Productline();

		productline.setProductline(String.valueOf(productlineCode));
		productline.setTextDescription(TEXT_DESCRIPTION);
		productline.setHtmlDescription(HTML_DESCRIPTION);
		productline.setImage(IMAGE);

		return productline;
	}

	public static DtoProductline dtoNewProductline() {

		DtoProductline dtoProductline = new DtoProductline();

		dtoProductline.setProductline(String.valueOf(NEW_PRODUCTLINE));
		dtoProductline.setTextDescription(NEW_TEXT_DESCRIPTION);
		dtoProductline.setHtmlDescription(NEW_HTML_DESCRIPTION);
		dtoProductline.setImage(NEW_IMAGE);

		return dtoProductline;
	}

	public static List<Productline> productlines() {

		List<Productline> productlines = new ArrayList<>();

		for (int productline = PRODUCTLINE_MIN; productline < PRODUCTLINE_MAX; productline++) {
			productlines.add(productline(productline));
		}

		return productlines;
	}

	public static void assertEquals(Productline productline, DtoProductline dtoProductline) {

		assertNotNull(productline, "Productline should not be null");
		assertNotNull(dtoProductline, "DTO Productline should not be null");

		assertProductlinesEquals(productline.getProductline(), dtoProductline.getProductline());
		assertModifiableFieldsEquals(productline, dtoProductline);
	}

	public static void assertUpdated(Productline original, DtoProductline dtoProductline, Productline productline) {

		assertProductlinesEquals(original.getProductline(), productline.getProductline());
		assertModifiableFieldsEquals(productline, dtoProductline);
	}

	private static void assertProductlinesEquals(String expected, String actual) {

		Assertions.assertEquals(expected, actual, "Productline should match");
	}

	private static void assertModifiableFieldsEquals(Productline productline, DtoProductline dtoProductline) {

		Assertions.assertEquals(productline.getTextDescription(), dtoProductline.getTextDescription(),
				"Text description should match");
		Assertions.assertEquals(productline.getHtmlDescription(), dtoProductline.getHtmlDescription(),
				"Html description should match");
		assertArrayEquals(productline.getImage(), dtoProductline.getImage(), "Image should match");
	}
}