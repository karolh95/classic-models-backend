package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}