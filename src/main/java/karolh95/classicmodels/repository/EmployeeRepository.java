package karolh95.classicmodels.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.EmployeeQuery;
import karolh95.classicmodels.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<EmployeeQuery.NameJobTitle> findAllBy();

	List<EmployeeQuery.NameJobTitleOffice> findByJobTitle(String jobTitle);

	List<EmployeeQuery.NameJobTitleOffice> findByOfficeCode(String officeCode);

	List<EmployeeQuery.NameJobTitleOffice> findByJobTitleAndOfficeCode(String jobTitle, String officeCode);

	List<EmployeeQuery.NameJobTitleOffice> findByJobTitleOrOfficeCode(String jobTitle, String officeCode, Sort sort);

	List<EmployeeQuery.NameJobTitleOffice> findByOfficeCodeBetween(String low, String high, Sort sort);

	List<EmployeeQuery.NameJobTitleOffice> findByOfficeCodeLessThan(String officeCode);

	List<EmployeeQuery.NameJobTitleOffice> findByOfficeCodeLessThanEqual(String officeCode);

	List<EmployeeQuery.NameJobTitleOffice> findByOfficeCodeIn(Collection<String> officeCodes);

	List<EmployeeQuery.NameJobTitleOffice> findByOfficeCodeGreaterThan(String officeCode);

	List<EmployeeQuery.NameJobTitle> findByLastNameLike(String lastName);

	List<EmployeeQuery.NameJobTitle> findByLastNameNotLike(String lastName);

	List<EmployeeQuery.NameJobTitle> findByLastNameStartingWith(String prefix);

	List<EmployeeQuery.NameJobTitle> findByLastNameContaining(String string);

	List<EmployeeQuery.NameJobTitle> findByLastNameNotContaining(String string);

	List<EmployeeQuery.NameJobTitle> findByLastNameEndingWith(String suffix);

	List<EmployeeQuery.NameJobTitle> findByReportsToIsNull();

	List<EmployeeQuery.NameJobTitle> findByJobTitleNot(String jobTitle);
}