package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.EmployeeRepositoryCustom;
import karolh95.classicmodels.model.Employee;

@Repository
public interface EmployeeRepository
		extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
}
