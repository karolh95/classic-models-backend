package karolh95.classicmodels.criteria;

import java.util.List;
import karolh95.classicmodels.dto.projection.office.CodeCityCountryPhone;

public interface OfficeRepositoryCustom {

	List<CodeCityCountryPhone> findInCountries(String... countries);

	List<CodeCityCountryPhone> findNotInCountries(String... countries);
}
