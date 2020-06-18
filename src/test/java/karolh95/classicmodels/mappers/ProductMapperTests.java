package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.mapper.ProductMapper;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.repository.ProductRepository;

@SpringBootTest
public class ProductMapperTests {
	
	@Autowired
	private ProductMapper mapper;

	@Autowired
	private ProductRepository repository;

	@Test
	@DisplayName("Should map Product to Dto")
	public void mapProductToDto(){

		Optional<Product> optional = this.repository.findById("S10_1678");

		assertTrue(optional.isPresent(), "Product should exist");

		Product product = optional.get();
		DtoProduct dto = this.mapper.productToDto(product);

		assertEquals(product.getProductCode(), dto.getProductCode(), "Product code should match");
		assertEquals(product.getProductName(), dto.getProductName(), "Product name should match");
		assertEquals(product.getProductScale(), dto.getProductScale(), "Product scale should match");
		assertEquals(product.getProductVendor(), dto.getProductVendor(), "Product vendor should match");
		assertEquals(product.getProductDescription(), dto.getProductDescription(), "Product description should match");
		assertEquals(product.getQuantityInStock(), dto.getQuantityInStock(), "Quantity in stock should match");
		assertEquals(product.getMSRP(), dto.getMSRP(), "MSRP should match");
		assertEquals(product.getProductline().getProductline(), dto.getProductline(), "Productline should match");
	}
}