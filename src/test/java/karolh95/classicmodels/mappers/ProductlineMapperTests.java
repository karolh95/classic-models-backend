package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.mapper.ProductlineMapper;
import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.repository.ProductlineRepository;

@SpringBootTest
public class ProductlineMapperTests {

	@Autowired
	private ProductlineMapper mapper;

	@Autowired
	private ProductlineRepository repository;

	@Test
	@DisplayName("Should map Productline to Dto")
	public void mapProductlineToDto() {

		Optional<Productline> optional = this.repository.findById("Classic Cars");

		assertTrue(optional.isPresent(), "Productline should exist");

		Productline productline = optional.get();
		DtoProductline dto = this.mapper.productlineToDto(productline);

		assertEquals(productline.getProductline(), dto.getProductline(), "Productline should match");
		assertEquals(productline.getTextDescription(), dto.getTextDescription(), "Text description should match");
		assertEquals(productline.getHtmlDescription(), dto.getHtmlDescription(), "Html description should match");
		assertArrayEquals(productline.getImage(), dto.getImage(), "Image should match");
	}
}