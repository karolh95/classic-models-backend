package karolh95.classicmodels.controller.report;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Office;
import karolh95.classicmodels.dto.projection.office.CodeCityCountryPhone;
import karolh95.classicmodels.service.raport.OfficeRaport;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Office.REPORT)
public class OfficeReports {

	private final OfficeRaport raport;

	@GetMapping(Office.Report.COUNTRIES_IN)
	public List<CodeCityCountryPhone> findByCountries(@PathVariable String country1,
			@PathVariable String country2) {

		return raport.getOfficesByCountries(country1, country2);
	}

	@GetMapping(Office.Report.COUNTRIES_NOT_IN)
	public List<CodeCityCountryPhone> findByCountriesNot(@PathVariable String country1,
			@PathVariable String country2) {

		return raport.getOfficesByCountriesNot(country1, country2);
	}
}