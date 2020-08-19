package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static karolh95.classicmodels.utils.ProductlineUtil.productline;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Productline;

public final class ProductUtil {

	private static final int PRODUCT_CODE_MIN = 1;
	private static final String PRODUCT_NAME = "productName";
	private static final String PRODUCT_SCALE = "productScale";
	private static final String PRODUCT_VENDOR = "productVendor";
	private static final String PRODUCT_DESCRIPTION = "productDescription";
	private static final int QUANTITY_IN_STOCK = 1;
	private static final BigDecimal BUY_PRICE = new BigDecimal("1.00");
	private static final BigDecimal MSRP = new BigDecimal("0.25");

	private static final int NEW_PRODUCT_CODE = 2;
	private static final String NEW_PRODUCT_NAME = "new_productName";
	private static final String NEW_PRODUCT_SCALE = "new_productScale";
	private static final String NEW_PRODUCT_VENDOR = "new_productVendor";
	private static final String NEW_PRODUCT_DESCRIPTION = "new_productDescription";
	private static final short NEW_QUANTITY_IN_STOCK = 2;
	private static final BigDecimal NEW_BUY_PRICE = new BigDecimal("2.00");
	private static final BigDecimal NEW_MSRP = new BigDecimal("0.50");

	private static final int PRODUCT_CODE_MAX = 6;

	private ProductUtil() {

	}

	public static Product product() {

		return product(PRODUCT_CODE_MIN);
	}

	private static Product product(int productCode) {

		Product product = new Product();

		product.setProductCode(String.valueOf(productCode));
		product.setProductName(PRODUCT_NAME);
		product.setProductScale(PRODUCT_SCALE);
		product.setProductVendor(PRODUCT_VENDOR);
		product.setProductDescription(PRODUCT_DESCRIPTION);
		product.setQuantityInStock(QUANTITY_IN_STOCK);
		product.setBuyPrice(BUY_PRICE);
		product.setMSRP(MSRP);

		Productline productline = productline();
		product.setProductline(productline);
		product.setProductline_id(productline.getProductline());

		return product;
	}

	public static List<Product> products() {

		List<Product> products = new ArrayList<>();

		for (int productCode = PRODUCT_CODE_MIN; productCode < PRODUCT_CODE_MAX; productCode++) {
			products.add(product(productCode));
		}

		return products;
	}

	public static DtoProduct dtoNewProduct() {

		DtoProduct dtoProduct = new DtoProduct();

		dtoProduct.setProductCode(String.valueOf(NEW_PRODUCT_CODE));
		dtoProduct.setProductName(NEW_PRODUCT_NAME);
		dtoProduct.setProductScale(NEW_PRODUCT_SCALE);
		dtoProduct.setProductVendor(NEW_PRODUCT_VENDOR);
		dtoProduct.setProductDescription(NEW_PRODUCT_DESCRIPTION);
		dtoProduct.setQuantityInStock(NEW_QUANTITY_IN_STOCK);
		dtoProduct.setBuyPrice(NEW_BUY_PRICE);
		dtoProduct.setMSRP(NEW_MSRP);

		return dtoProduct;
	}

	public static void assertEquals(Product product, DtoProduct dtoProduct) {

		assertEqualsWithoutProductline(product, dtoProduct);
		assertProductlinesEquals(product.getProductline(), dtoProduct.getProductline());
	}

	public static void assertEqualsWithoutProductline(Product product, DtoProduct dtoProduct) {

		assertNotNull(product, "Product should not be null");
		assertNotNull(dtoProduct, "DTO Product should not be null");

		assertProductsCodesEquals(product.getProductCode(), dtoProduct.getProductCode());
		assertModifiableFieldsEquals(product, dtoProduct);
	}

	public static void assertUpdated(Product original, DtoProduct dtoProduct, Product product) {

		assertProductsCodesEquals(original.getProductCode(), product.getProductCode());
		assertModifiableFieldsEquals(product, dtoProduct);
	}

	private static void assertProductsCodesEquals(String expected, String actual) {

		Assertions.assertEquals(expected, actual, "Product code should match");
	}

	private static void assertModifiableFieldsEquals(Product product, DtoProduct dtoProduct) {

		Assertions.assertEquals(dtoProduct.getProductName(), product.getProductName(),
				"Product name should match");
		Assertions.assertEquals(dtoProduct.getProductScale(), product.getProductScale(),
				"Product scale should match");
		Assertions.assertEquals(dtoProduct.getProductVendor(), product.getProductVendor(),
				"Product vendor should match");
		Assertions.assertEquals(dtoProduct.getProductDescription(), product.getProductDescription(),
				"Product description should match");
		Assertions.assertEquals(dtoProduct.getQuantityInStock(), product.getQuantityInStock(),
				"Quantity in stock should match");
		Assertions.assertEquals(dtoProduct.getBuyPrice(), product.getBuyPrice(),
				"Buy price should match");
		Assertions.assertEquals(dtoProduct.getMSRP(), product.getMSRP(), "MSRP should match");
	}

	private static void assertProductlinesEquals(Productline productline, String actual) {

		assertNotNull(productline, "Productline shoud not be null");
		Assertions.assertEquals(productline.getProductline(), actual, "Productline should match");
	}
}
