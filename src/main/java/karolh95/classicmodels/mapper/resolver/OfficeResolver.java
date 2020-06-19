package karolh95.classicmodels.mapper.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.repository.OfficeRepository;

@Component
public class OfficeResolver {

	@Autowired
	private OfficeRepository repository;

	public Office map(String officeCode) {

		if (officeCode == null) {
			return null;
		}

		Optional<Office> optional = repository.findById(officeCode);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			
			return null;
		}
	}
}