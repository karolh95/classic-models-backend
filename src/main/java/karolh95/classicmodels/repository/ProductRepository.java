package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
}