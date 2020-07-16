package karolh95.classicmodels.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.mapper.ProductlineMapper;
import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.repository.ProductlineRepository;

@SpringBootTest
public class ProductlineServiceTests {

	@MockBean
	ProductlineRepository repository;

	@Autowired
	ProductlineMapper mapper;

	@Autowired
	ProductlineService service;

	@Test
	@DisplayName("Should create new Productline")
	public void createProductline() {

		when(repository.getOne(anyString())).thenThrow(new EntityNotFoundException());
		when(repository.save(any(Productline.class))).thenReturn(productline());

		DtoProductline productline = dtoProductline();
		DtoProductline response = service.saveProductline(productline);

		assertEquals(productline, response, "Productline and response should match");
	}

	@Test
	@DisplayName("Should update existing Productline")
	public void updateProductline() {

		when(repository.getOne(anyString())).thenReturn(productline());
		when(repository.save(any(Productline.class))).thenReturn(modyfiedProductline());

		DtoProductline productline = modifiedDtoProductline();
		DtoProductline response = service.saveProductline(productline);

		assertEquals(productline, response, "Productline and response should match");

	}

	private Productline productline() {

		Productline productline = new Productline();
		productline.setProductline("productline");
		productline.setTextDescription("textDescription");
		productline.setHtmlDescription("htmlDescription");
		productline.setImage(new byte[1]);

		return productline;
	}

	private DtoProductline dtoProductline() {
		return mapper.productlineToDto(productline());
	}

	private DtoProductline modifiedDtoProductline() {

		Productline productline = modyfiedProductline();
		return mapper.productlineToDto(productline);
	}

	private Productline modyfiedProductline() {

		Productline productline = productline();
		productline.setTextDescription("modyfied textDescription");
		productline.setHtmlDescription("modyfied htmlDescription");

		return productline;
	}
}