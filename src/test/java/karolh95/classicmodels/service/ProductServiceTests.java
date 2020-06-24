package karolh95.classicmodels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.mapper.ProductMapper;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTests {

	@MockBean
	private ProductRepository repository;

	@Autowired
	private ProductMapper mapper;

	@Autowired
	private ProductService service;

	@Test
	@DisplayName("Should create new Product")
	public void createNewProduct() {

		when(repository.getOne(anyString())).thenThrow(new EntityNotFoundException());
		when(repository.save(any(Product.class))).thenReturn(product());

		DtoProduct product = dtoProduct();
		DtoProduct response = service.saveProduct(product);

		assertEquals(product, response, "Product and response should match");
	}

	@Test
	@DisplayName("Should update existing Product")
	public void updateProduct() {

		when(repository.getOne(anyString())).thenReturn(product());
		when(repository.save(any(Product.class))).thenReturn(modifiedProduct());

		DtoProduct product = modifiedDtoProduct();
		DtoProduct response = service.saveProduct(product);

		assertEquals(product, response, "Product and response should match");
	}

	private Product product() {

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

	private Productline productline() {

		Productline productline = new Productline();
		productline.setProductline("productline");

		return productline;
	}

	private DtoProduct dtoProduct() {

		return mapper.productToDto(product());
	}

	private DtoProduct modifiedDtoProduct() {

		return mapper.productToDto(modifiedProduct());
	}

	private Product modifiedProduct() {

		Product product = product();
		product.setProductName("newProductName");
		product.setProductScale("newProductScale");
		product.setProductVendor("newProductVendor");
		product.setProductDescription("newProductDescription");
		product.setQuantityInStock((short) 5);
		product.setBuyPrice(new BigDecimal("5.00"));
		product.setMSRP(new BigDecimal("0.50"));
		product.setProductline(newProductline());

		return product;
	}

	private Productline newProductline() {

		Productline productline = new Productline();
		productline.setProductline("newProductline");

		return productline;
	}
}