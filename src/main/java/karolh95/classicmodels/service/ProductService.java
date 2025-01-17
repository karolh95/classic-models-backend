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

		List<Product> products = repository.findAll();
		return mapper.productsToDtos(products);
	}

	public DtoProduct getProduct(String productCode) {

		Optional<Product> optional = repository.findById(productCode);

		if (optional.isEmpty()) {
			return null;
		}

		Product product = optional.get();
		return mapper.productToDto(product);
	}

	public DtoProduct saveProduct(DtoProduct dtoProduct) {

		Product product = getOne(dtoProduct.getProductCode());

		mapper.updateFromDto(dtoProduct, product);

		if (!product.hasValidIds()) {
			return null;
		}

		product = repository.save(product);

		return mapper.productToDto(product);
	}

	private Product getOne(String productCode) {

		Optional<Product> optional = repository.findById(productCode);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			Product product = new Product();

			product.setProductCode(productCode);

			return product;
		}
	}
}
