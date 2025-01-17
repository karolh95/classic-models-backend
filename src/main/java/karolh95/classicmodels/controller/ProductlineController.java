package karolh95.classicmodels.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Mappings;
import karolh95.classicmodels.controller.mapping.Productline;
import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.service.ProductlineService;

@RestController
@RequestMapping(Mappings.PRODUCTLINE)
public class ProductlineController {

	@Autowired
	private ProductlineService service;

	@GetMapping(Productline.ALL)
	public List<DtoProductline> getAllProductlines() {

		return service.getAllProductlines();
	}

	@GetMapping(Productline.GET)
	public ResponseEntity<DtoProductline> getProductline(@PathVariable String id) {

		DtoProductline response = service.getProductline(id);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(Productline.SAVE)
	public ResponseEntity<DtoProductline> save(@Valid @RequestBody DtoProductline productline,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(productline);
		}

		DtoProductline response = service.saveProductline(productline);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}
