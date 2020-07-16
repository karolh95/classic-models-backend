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

import karolh95.classicmodels.mapper.resolver.ProductlineResolver;
import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.repository.ProductlineRepository;
import karolh95.classicmodels.utils.ProductlineUtil;

class ProductlineResolverTests {

	@Mock
	ProductlineRepository repository;

	@InjectMocks
	ProductlineResolver resolver;

	String id;
	Productline productline;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		id = "id";
	}

	@Test
	@DisplayName("Should not map null id")
	void mapNullId() {

		id = null;

		productline = resolver.map(id);

		assertNull(productline, "Productline should be null");
	}

	@Test
	@DisplayName("Should not map id when Productline not exists")
	void mapIdToNonExistingProductline() {

		when(repository.findById(anyString())).thenReturn(empty());

		productline = resolver.map(id);

		assertNull(productline, "Productline should be null");
	}

	@Test
	@DisplayName("Should map id when Productline exists")
	void mapIdToExistingProductline() {

		when(repository.findById(anyString())).thenReturn(optional());

		productline = resolver.map(id);

		assertNotNull(productline, "Productline should not be null");
	}

	private Optional<Productline> empty() {
		return Optional.empty();
	}

	private Optional<Productline> optional() {
		return Optional.of(ProductlineUtil.productline());
	}
}