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

import karolh95.classicmodels.mapper.resolver.OfficeResolver;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.repository.OfficeRepository;
import karolh95.classicmodels.utils.OfficeUtil;

public class OfficeResolverTests {

	@Mock
	OfficeRepository repository;

	@InjectMocks
	OfficeResolver resolver;

	String officeCode;
	Office office;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		officeCode = "1";
	}

	@Test
	@DisplayName("Should not map null officeCode")
	void mapNullOfficeCode() {

		officeCode = null;

		office = resolver.map(officeCode);

		assertNull(office, "Office should be null");
	}

	@Test
	@DisplayName("Should not map officeCode when Office not exists")
	void mapOfficeCodeToNonExistingOffice() {

		when(repository.findById(anyString())).thenReturn(empty());

		office = resolver.map(officeCode);

		assertNull(office, "Office should be null");
	}

	@Test
	@DisplayName("Should map officeCode when Office exists")
	void mapOfficeCodeToExistingOffice() {

		when(repository.findById(anyString())).thenReturn(optional());

		office = resolver.map(officeCode);

		assertNotNull(office, "Office should not be null");
	}

	private Optional<Office> empty() {
		return Optional.empty();
	}

	private Optional<Office> optional() {
		return Optional.of(OfficeUtil.office());
	}

}