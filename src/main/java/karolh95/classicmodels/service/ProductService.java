package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.mapper.ProductMapper;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper mapper;
	private final ProductRepository repository;

	public List<DtoProduct> getAllProducts() {

		List<Product> products = this.repository.findAll();
		return this.mapper.productsToDtos(products);
	}

	public DtoProduct getProduct(String productCode) {

		Optional<Product> optional = this.repository.findById(productCode);

		if (optional.isEmpty()) {
			return null;
		}
		
		Product product = optional.get();
		return this.mapper.productToDto(product);
	}
}