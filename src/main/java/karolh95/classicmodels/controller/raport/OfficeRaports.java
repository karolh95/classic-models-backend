package karolh95.classicmodels.controller.raport;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.OfficeQuery;
import karolh95.classicmodels.service.OfficeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("office/raports")
public class OfficeRaports {

	private final OfficeService service;

	@GetMapping("byCountries/{country1}/{country2}")
	public List<OfficeQuery.CodeCityCountryPhone> findByCountries(@PathVariable String country1,
			@PathVariable String country2) {

		return service.getOfficesByCountries(country1, country2);
	}

	@GetMapping("byCountriesNot/{country1}/{country2}")
	public List<OfficeQuery.CodeCityCountryPhone> findByCountriesNot(@PathVariable String country1,
			@PathVariable String country2) {

		return service.getOfficesByCountriesNot(country1, country2);
	}
}
