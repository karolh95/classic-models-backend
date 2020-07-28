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
import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("all")
	public List<DtoProduct> getAllProducts() {

		return service.getAllProducts();
	}

	@GetMapping("detail/{productCode}")
	public ResponseEntity<DtoProduct> getProduct(@PathVariable String productCode) {

		DtoProduct response = service.getProduct(productCode);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("save")
	public ResponseEntity<DtoProduct> save(@Valid @RequestBody DtoProduct product,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(product);
		}

		DtoProduct response = service.saveProduct(product);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}
