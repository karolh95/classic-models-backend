package karolh95.classicmodels.repository;

import java.util.Collection;
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

	List<EmployeeOfficeSummary> findByJobTitle(String jobTitle);

	List<EmployeeOfficeSummary> findByOfficeCode(String officeCode);

	List<EmployeeOfficeSummary> findByJobTitleAndOfficeCode(String jobTitle, String officeCode);

	List<EmployeeOfficeSummary> findByJobTitleOrOfficeCode(String jobTitle, String officeCode, Sort sort);

	List<EmployeeOfficeSummary> findByOfficeCodeBetween(String low, String high, Sort sort);

	List<EmployeeOfficeSummary> findByOfficeCodeLessThan(String officeCode);

	List<EmployeeOfficeSummary> findByOfficeCodeLessThanEqual(String officeCode);

	List<EmployeeOfficeSummary> findByOfficeCodeIn(Collection<String> officeCodes);

	List<EmployeeOfficeSummary> findByOfficeCodeGreaterThan(String officeCode);

	List<EmployeeSummary> findByLastNameLike(String lastName);

	List<EmployeeSummary> findByLastNameNotLike(String lastName);

	List<EmployeeSummary> findByLastNameStartingWith(String prefix);

	List<EmployeeSummary> findByLastNameContaining(String string);

	List<EmployeeSummary> findByLastNameNotContaining(String string);

	List<EmployeeSummary> findByLastNameEndingWith(String suffix);

	List<EmployeeSummary> findByReportsToIsNull();

	List<EmployeeSummary> findByJobTitleNot(String jobTitle);
}