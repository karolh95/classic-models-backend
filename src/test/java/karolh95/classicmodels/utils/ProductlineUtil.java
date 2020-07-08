package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.model.Productline;

public class ProductlineUtil {

	public static Productline productline() {

		Productline productline = new Productline();

		productline.setProductline("productline");
		productline.setTextDescription("textDescription");
		productline.setHtmlDescription("htmlDescription");
		productline.setImage(new byte[1]);

		return productline;
	}

	public static DtoProductline dtoProductline() {

		DtoProductline dtoProductline = new DtoProductline();

		dtoProductline.setProductline("productline");
		dtoProductline.setTextDescription("textDescription");
		dtoProductline.setHtmlDescription("htmlDescription");
		dtoProductline.setImage(new byte[1]);

		return dtoProductline;
	}

	public static List<Productline> productlines() {

		Productline productline = productline();
		List<Productline> productlines = new ArrayList<>(3);

		productlines.add(productline);
		productlines.add(productline);
		productlines.add(productline);

		return productlines;
	}

	public static void assertEquals(Productline productline, DtoProductline dtoProductline) {

		assertNotNull(productline, "Productline should not be null");
		assertNotNull(dtoProductline, "DTO Productline should not be null");

		Assertions.assertEquals(productline.getProductline(), dtoProductline.getProductline(),
				"Productline should match");
		Assertions.assertEquals(productline.getTextDescription(), dtoProductline.getTextDescription(),
				"Text description should match");
		Assertions.assertEquals(productline.getHtmlDescription(), dtoProductline.getHtmlDescription(),
				"Html description should match");
		assertArrayEquals(productline.getImage(), dtoProductline.getImage(), "Image should match");
	}

	public static void assertUpdated(Productline original, DtoProductline dtoProductline, Productline productline) {

		Assertions.assertEquals(original.getProductline(), productline.getProductline(), "Productline should match");
		Assertions.assertEquals(dtoProductline.getHtmlDescription(), productline.getHtmlDescription(),
				"HtmlDescription should match");
		Assertions.assertEquals(dtoProductline.getTextDescription(), productline.getTextDescription(),
				"TextDescription should match");
		assertArrayEquals(dtoProductline.getImage(), productline.getImage(), "Image should match");
	}
}