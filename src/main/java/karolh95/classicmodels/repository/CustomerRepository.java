package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.CustomerContact;
import karolh95.classicmodels.dto.query.CustomerState;
import karolh95.classicmodels.dto.query.CustomerStateCity;
import karolh95.classicmodels.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<CustomerContact> findAllByOrderByContactLastName();

	List<CustomerContact> findAllByOrderByContactLastNameDesc();

	List<CustomerContact> findAllBy(Sort sort);

	List<CustomerState> findDistinctAddress_StateBy();

	List<CustomerStateCity> findDistinctByAddress_StateNotNull(Sort sort);

	List<CustomerState> findDistinctAddress_StateByAddress_Country(String country);

	List<CustomerState> findFirst5DistinctAddress_StateByAddress_StateNotNull();
}