package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.CustomerRepositoryCustom;
import karolh95.classicmodels.dto.query.CustomerQuery;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.service.NthByField.Find;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,
		Find<CustomerQuery.NameCreditLimit>, CustomerRepositoryCustom {
}
