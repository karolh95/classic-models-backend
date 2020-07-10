package karolh95.classicmodels.mappers;

import static karolh95.classicmodels.utils.ProductUtil.assertEquals;
import static karolh95.classicmodels.utils.ProductUtil.assertEqualsWithoutProductline;
import static karolh95.classicmodels.utils.ProductUtil.assertUpdated;
import static karolh95.classicmodels.utils.ProductUtil.dtoNewProduct;
import static karolh95.classicmodels.utils.ProductUtil.product;
import static karolh95.classicmodels.utils.ProductUtil.products;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.mapper.ProductMapper;
import karolh95.classicmodels.mapper.ProductMapperImpl;
import karolh95.classicmodels.mapper.resolver.ProductlineResolver;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.utils.ProductlineUtil;

class ProductMapperTests {

	@Mock
	ProductlineResolver resolver;

	@InjectMocks
	ProductMapper mapper = new ProductMapperImpl();

	@Nested
	class ProductToDtoTest {

		Product product;

		@BeforeEach
		void init() {

			product = product();
		}

		@Test
		@DisplayName("Should not map null object")
		void mapNullProductToDto() {

			product = null;

			DtoProduct dtoProduct = mapper.productToDto(product);

			assertNull(dtoProduct, "DtoProduct should be null");
		}

		@Test
		@DisplayName("Should map Product without Productline to dto")
		void mapProductWithoutProductlineToDto() {

			product.setProductline(null);

			DtoProduct dtoProduct = mapper.productToDto(product);

			assertEqualsWithoutProductline(product, dtoProduct);
		}

		@Test
		@DisplayName("Should map Product to Dto")
		void mapProductToDto() {

			Product product = product();

			DtoProduct dtoProduct = mapper.productToDto(product);

			assertEquals(product, dtoProduct);
		}
	}

	@Nested
	class ProductsToDtosTest {

		List<Product> products;
		List<DtoProduct> dtoProducts;

		@BeforeEach
		void init() {
			products = products();
		}

		@Test
		@DisplayName("Should not map null product list to dtos")
		void mapNullProductsToDtos() {

			products = null;

			dtoProducts = mapper.productsToDtos(products);

			assertNull(dtoProducts, "Dto product list should be null");
		}

		@Test
		@DisplayName("Should map empty product list to dtos")
		void mapEmptyProductsToDtos() {

			products.clear();

			dtoProducts = mapper.productsToDtos(products);

			assertTrue(dtoProducts.isEmpty(), "Dto product list should not contain elements");
		}

		@Test
		@DisplayName("Should map Product list to dtos")
		void mapProductsToDtos() {

			dtoProducts = mapper.productsToDtos(products);

			for (int i = 0; i < products.size(); i++) {

				Product product = products.get(i);
				DtoProduct dtoProduct = dtoProducts.get(i);

				assertEquals(product, dtoProduct);
			}
		}
	}

	@Nested
	class UpdateFromDtoTest {

		final Product original = product();
		Product product;
		DtoProduct dtoProduct;

		@BeforeEach
		void init() {

			MockitoAnnotations.initMocks(ProductMapperTests.this);
			product = product();
			dtoProduct = dtoNewProduct();
		}

		@Test
		@DisplayName("Should not update Product when Dto is null")
		void updateProductWithNullDto() {

			dtoProduct = null;

			mapper.updateFromDto(dtoProduct, product);

			assertEquals(original, product, "Product should not be updated");
		}

		@Test
		@DisplayName("Should update Product from Dto")
		void updateFromDto() {

			when(resolver.map(anyString())).thenReturn(ProductlineUtil.productline());

			mapper.updateFromDto(dtoProduct, product);

			assertUpdated(original, dtoProduct, product);
		}
	}

}