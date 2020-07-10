package karolh95.classicmodels.mapper.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.repository.ProductRepository;

@Component
public class ProductResolver {

	@Autowired
	private ProductRepository repository;

	public Product map(String productCode) {

		if (productCode == null) {
			return null;
		}

		Optional<Product> optional = repository.findById(productCode);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			return null;
		}
	}
}