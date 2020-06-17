package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import karolh95.classicmodels.model.Productline;

public interface ProductlineRepository extends JpaRepository<Productline, String>{
	
}