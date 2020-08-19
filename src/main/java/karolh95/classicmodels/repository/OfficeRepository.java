package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.OfficeRepositoryCustom;
import karolh95.classicmodels.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, String>, OfficeRepositoryCustom {
}
