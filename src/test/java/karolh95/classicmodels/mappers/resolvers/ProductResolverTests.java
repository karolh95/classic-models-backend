package karolh95.classicmodels.mappers.resolvers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.mapper.resolver.ProductResolver;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.repository.ProductRepository;
import karolh95.classicmodels.utils.ProductUtil;

class ProductResolverTests {

	@Mock
	ProductRepository repository;

	@InjectMocks
	ProductResolver resolver;

	String productCode;
	Product product;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		productCode = "code";
	}

	@Test
	@DisplayName("Should not map null productCode")
	void mapNullProductCode() {

		productCode = null;

		product = resolver.map(productCode);

		assertNull(product, "Product should be null");
	}

	@Test
	@DisplayName("Should not map productCode when Product not exists")
	void mapProductCodeToNonExistingProduct() {

		when(repository.findById(anyString())).thenReturn(empty());

		product = resolver.map(productCode);

		assertNull(product, "Product should be null");
	}

	@Test
	@DisplayName("Should map productcode when Product exists")
	void mapProductCodeToExistingProduct() {

		when(repository.findById(anyString())).thenReturn(optional());

		product = resolver.map(productCode);

		assertNotNull(product, "Product should not be null");
	}

	private Optional<Product> empty() {
		return Optional.empty();
	}

	private Optional<Product> optional() {
		return Optional.of(ProductUtil.product());
	}
}