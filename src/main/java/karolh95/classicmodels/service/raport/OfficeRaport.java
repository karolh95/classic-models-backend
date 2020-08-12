package karolh95.classicmodels.service.raport;

import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.projection.office.CodeCityCountryPhone;
import karolh95.classicmodels.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficeRaport {

	private final OfficeRepository repository;

	public List<CodeCityCountryPhone> getOfficesByCountries(String... countries) {

		return repository.findInCountries(countries);
	}

	public List<CodeCityCountryPhone> getOfficesByCountriesNot(String... countries) {

		return repository.findNotInCountries(countries);
	}
}
