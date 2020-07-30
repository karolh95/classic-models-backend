package karolh95.classicmodels.controller.raport;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.dto.query.OfficeQuery;
import karolh95.classicmodels.service.raport.OfficeRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("office/raports")
public class OfficeRaports {

	private final OfficeRaport raport;

	@GetMapping("byCountries/{country1}/{country2}")
	public List<OfficeQuery.CodeCityCountryPhone> findByCountries(@PathVariable String country1,
			@PathVariable String country2) {

		return raport.getOfficesByCountries(country1, country2);
	}

	@GetMapping("byCountriesNot/{country1}/{country2}")
	public List<OfficeQuery.CodeCityCountryPhone> findByCountriesNot(@PathVariable String country1,
			@PathVariable String country2) {

		return raport.getOfficesByCountriesNot(country1, country2);
	}
}
