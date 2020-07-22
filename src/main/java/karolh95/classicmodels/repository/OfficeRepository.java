package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.OfficeDetail;
import karolh95.classicmodels.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, String> {

	List<OfficeDetail> findByAddress_CountryIn(String... countries);

	List<OfficeDetail> findByAddress_CountryNotIn(String... countries);
}