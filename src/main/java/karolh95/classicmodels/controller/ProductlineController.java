package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.service.ProductlineService;

@RestController
@RequestMapping("productlines")
public class ProductlineController {

	@Autowired
	private ProductlineService service;

	@GetMapping("all")
	public List<DtoProductline> getAllProductlines() {

		return service.getAllProductlines();
	}

	@GetMapping("detail/{productline}")
	public ResponseEntity<DtoProductline> getProductline(@PathVariable String productline) {

		DtoProductline response = service.getProductline(productline);
		
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("save")
	public ResponseEntity<DtoProductline> save(@RequestBody DtoProductline productline) {

		DtoProductline response = service.saveProductline(productline);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}