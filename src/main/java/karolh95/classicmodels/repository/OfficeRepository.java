package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.OfficeQuery;
import karolh95.classicmodels.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, String> {

	List<OfficeQuery.CodeCityCountryPhone> findByAddress_CountryIn(String... countries);

	List<OfficeQuery.CodeCityCountryPhone> findByAddress_CountryNotIn(String... countries);
}