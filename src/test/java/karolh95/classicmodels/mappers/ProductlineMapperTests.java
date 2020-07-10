package karolh95.classicmodels.mappers;

import static karolh95.classicmodels.utils.ProductlineUtil.assertEquals;
import static karolh95.classicmodels.utils.ProductlineUtil.assertUpdated;
import static karolh95.classicmodels.utils.ProductlineUtil.dtoNewProductline;
import static karolh95.classicmodels.utils.ProductlineUtil.productline;
import static karolh95.classicmodels.utils.ProductlineUtil.productlines;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.mapper.ProductlineMapper;
import karolh95.classicmodels.mapper.ProductlineMapperImpl;
import karolh95.classicmodels.model.Productline;

class ProductlineMapperTests {

	ProductlineMapper mapper = new ProductlineMapperImpl();

	@Nested
	@DisplayName("Productline to Dto tests")
	class ProductlineToDtoTest {

		Productline productline;

		@BeforeEach
		void init() {

			productline = productline();
		}

		@Test
		@DisplayName("Should not map null Productline object")
		void mapNullObject() {

			DtoProductline dtoProductline = mapper.productlineToDto(null);

			assertNull(dtoProductline, "DtoProductline should be null");
		}

		@Test
		@DisplayName("Should map Productline without Image to Dto")
		void mapProductlineWithoutImage() {

			productline.setImage(null);

			DtoProductline dtoProductline = mapper.productlineToDto(productline);

			assertEquals(productline, dtoProductline);
		}

		@Test
		@DisplayName("Should map Productline to Dto")
		void mapProductlineToDto() {

			DtoProductline dtoProductline = mapper.productlineToDto(productline);

			assertEquals(productline, dtoProductline);
		}
	}

	@Nested
	@DisplayName("Productlines to Dtos tests")
	class ProductlinesToDtosTest {

		@Test
		@DisplayName("Should not map null list")
		void mapNullProductlineList() {

			List<DtoProductline> dtoProductlines = mapper.productlinesToDtos(null);

			assertNull(dtoProductlines, "List of DtoProducline should be null");
		}

		@Test
		@DisplayName("Should map empty list")
		void mapEmptyList() {

			List<Productline> productlines = new ArrayList<>();

			List<DtoProductline> dtoProductlines = mapper.productlinesToDtos(productlines);

			assertEquals(dtoProductlines.size(), 0, "Size of DtoProductline list should be 0");
		}

		@Test
		@DisplayName("Should map Productlines list to dtos list")
		void mapProductlinesToDtos() {

			List<Productline> productlines = productlines();

			List<DtoProductline> dtoProductlines = mapper.productlinesToDtos(productlines);

			assertNotNull(dtoProductlines, "List of DtoProductline should not be null");
			assertEquals(productlines.size(), dtoProductlines.size(), "Size of lists should match");

			for (int i = 0; i < productlines.size(); i++) {

				Productline productline = productlines.get(i);
				DtoProductline dtoProductline = dtoProductlines.get(i);

				assertEquals(productline, dtoProductline);
			}
		}

	}

	@Nested
	@DisplayName("Update from Dto tests")
	class UpdateFromDtoTest {

		final Productline original = productline();
		Productline productline;
		DtoProductline dtoProductline;

		@BeforeEach
		void init() {
			productline = productline();
			dtoProductline = dtoNewProductline();
		}

		@Test
		@DisplayName("Should not update Productline from null object")
		void updateProductlineFromNullDto() {

			mapper.updateFromDto(null, productline);

			assertEquals(original, productline, "Productline should not change");
		}

		@Test
		@DisplayName("Should update Productline from Dto")
		void updateProductlineFromDto() {

			mapper.updateFromDto(dtoProductline, productline);

			assertUpdated(original, dtoProductline, productline);
		}

		@Test
		@DisplayName("Should update Productline from Dto with NULL image")
		void updateProductlineFromDtoWithNullImage() {

			dtoProductline.setImage(null);

			mapper.updateFromDto(dtoProductline, productline);

			assertUpdated(original, dtoProductline, productline);
		}
	}
}