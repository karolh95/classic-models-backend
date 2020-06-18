package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("all")
	public List<DtoProduct> getAllProducts() {

		return this.productService.getAllProducts();
	}

	@GetMapping("detail/{productCode}")
	public ResponseEntity<DtoProduct> getProduct(@PathVariable String productCode) {
		
		DtoProduct response = this.productService.getProduct(productCode);
		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);

	}
}