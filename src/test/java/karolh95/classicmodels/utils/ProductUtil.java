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

public class ProductUtil {

	public static Product product() {

		Product product = new Product();

		product.setProductCode("productCode");
		product.setProductName("productName");
		product.setProductScale("productScale");
		product.setProductVendor("productVendor");
		product.setProductDescription("productDescription");
		product.setQuantityInStock((short) 0);
		product.setBuyPrice(new BigDecimal("1.00"));
		product.setMSRP(new BigDecimal("0.25"));

		product.setProductline(productline());

		return product;
	}

	public static List<Product> products() {

		List<Product> products = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			products.add(product());
		}

		return products;
	}

	public static DtoProduct dtoNewProduct() {

		DtoProduct dtoProduct = new DtoProduct();

		dtoProduct.setProductCode("new_productCode");
		dtoProduct.setProductName("new_productName");
		dtoProduct.setProductScale("new_productScale");
		dtoProduct.setProductVendor("new_productVendor");
		dtoProduct.setProductDescription("new_productDescription");
		dtoProduct.setQuantityInStock((short) 1);
		dtoProduct.setBuyPrice(new BigDecimal("2.00"));
		dtoProduct.setMSRP(new BigDecimal("0.50"));

		return dtoProduct;
	}

	public static void assertEquals(Product product, DtoProduct dtoProduct) {

		assertEqualsWithoutProductline(product, dtoProduct);

		Productline productline = product.getProductline();

		assertNotNull(productline, "Productline shoud not be null");
		Assertions.assertEquals(productline.getProductline(), dtoProduct.getProductline(), "Productline should match");
	}

	public static void assertEqualsWithoutProductline(Product product, DtoProduct dtoProduct) {

		assertNotNull(product, "Product should not be null");
		assertNotNull(dtoProduct, "DTO Product should not be null");

		Assertions.assertEquals(product.getProductCode(), dtoProduct.getProductCode(), "Product code should match");
		Assertions.assertEquals(product.getProductName(), dtoProduct.getProductName(), "Product name should match");
		Assertions.assertEquals(product.getProductScale(), dtoProduct.getProductScale(), "Product scale should match");
		Assertions.assertEquals(product.getProductVendor(), dtoProduct.getProductVendor(),
				"Product vendor should match");
		Assertions.assertEquals(product.getProductDescription(), dtoProduct.getProductDescription(),
				"Product description should match");
		Assertions.assertEquals(product.getQuantityInStock(), dtoProduct.getQuantityInStock(),
				"Quantity in stock should match");
		Assertions.assertEquals(product.getMSRP(), dtoProduct.getMSRP(), "MSRP should match");
	}

	public static void assertUpdated(Product original, DtoProduct dtoProduct, Product product) {

		Assertions.assertEquals(original.getProductCode(), product.getProductCode(), "Product code should match");

		Assertions.assertEquals(dtoProduct.getProductName(), product.getProductName(), "Product name should match");
		Assertions.assertEquals(dtoProduct.getProductScale(), product.getProductScale(), "Product scale should match");
		Assertions.assertEquals(dtoProduct.getProductVendor(), product.getProductVendor(),
				"Product vendor should match");
		Assertions.assertEquals(dtoProduct.getProductDescription(), product.getProductDescription(),
				"Product description should match");
		Assertions.assertEquals(dtoProduct.getQuantityInStock(), product.getQuantityInStock(),
				"Quantity in stock should match");
		Assertions.assertEquals(dtoProduct.getBuyPrice(), product.getBuyPrice(), "Buy price should match");
		Assertions.assertEquals(dtoProduct.getMSRP(), product.getMSRP(), "MSRP should match");
	}
}