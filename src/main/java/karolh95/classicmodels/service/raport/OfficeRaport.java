package karolh95.classicmodels.service.raport;

import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.query.OfficeQuery;
import karolh95.classicmodels.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficeRaport {

	private final OfficeRepository repository;

	public List<OfficeQuery.CodeCityCountryPhone> getOfficesByCountries(String... countries) {

		return repository.findByAddress_CountryIn(countries);
	}

	public List<OfficeQuery.CodeCityCountryPhone> getOfficesByCountriesNot(String... countries) {

		return repository.findByAddress_CountryNotIn(countries);
	}
}
