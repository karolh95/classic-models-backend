package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.EmployeeOfficeSummary;
import karolh95.classicmodels.dto.query.EmployeeSummary;
import karolh95.classicmodels.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<EmployeeSummary> findAllBy();

	List<EmployeeOfficeSummary> findAllByJobTitle(String jobTitle);

	List<EmployeeOfficeSummary> findAllByOfficeCode(String officeCode);

	List<EmployeeOfficeSummary> findAllByJobTitleAndOfficeCode(String jobTitle, String officeCode);

	List<EmployeeOfficeSummary> findAllByJobTitleOrOfficeCode(String jobTitle, String officeCode, Sort sort);

	List<EmployeeOfficeSummary> findByOfficeCodeBetween(String low, String high, Sort sort);

	List<EmployeeOfficeSummary> findAllByOfficeCodeLessThan(String officeCode);

	List<EmployeeOfficeSummary> findAllByOfficeCodeGreaterThan(String officeCode);
}