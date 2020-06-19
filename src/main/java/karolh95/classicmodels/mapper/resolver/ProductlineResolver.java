package karolh95.classicmodels.mapper.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.repository.ProductlineRepository;

@Component
public class ProductlineResolver {

	@Autowired
	private ProductlineRepository repository;

	public Productline map(String productline) {

		if (productline == null) {
			return null;
		}

		Optional<Productline> optional = repository.findById(productline);

		if (optional.isPresent()) {

			return optional.get();

		} else {

			return null;
		}
	}
}