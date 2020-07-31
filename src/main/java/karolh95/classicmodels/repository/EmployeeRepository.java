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

	List<EmployeeQuery.NameJob> findAllBy();

	List<EmployeeQuery.NameJobOffice> findByJobTitle(String jobTitle);

	List<EmployeeQuery.NameJobOffice> findByOfficeCode(String officeCode);

	List<EmployeeQuery.NameJobOffice> findByJobTitleAndOfficeCode(String jobTitle,
			String officeCode);

	List<EmployeeQuery.NameJobOffice> findByJobTitleOrOfficeCode(String jobTitle, String officeCode,
			Sort sort);

	List<EmployeeQuery.NameJobOffice> findByOfficeCodeBetween(String low, String high, Sort sort);

	List<EmployeeQuery.NameJobOffice> findByOfficeCodeLessThan(String officeCode);

	List<EmployeeQuery.NameJobOffice> findByOfficeCodeLessThanEqual(String officeCode);

	List<EmployeeQuery.NameJobOffice> findByOfficeCodeIn(Collection<String> officeCodes);

	List<EmployeeQuery.NameJobOffice> findByOfficeCodeGreaterThan(String officeCode);

	List<EmployeeQuery.NameJob> findByLastNameLike(String lastName);

	List<EmployeeQuery.NameJob> findByLastNameNotLike(String lastName);

	List<EmployeeQuery.NameJob> findByLastNameStartingWith(String prefix);

	List<EmployeeQuery.NameJob> findByLastNameContaining(String string);

	List<EmployeeQuery.NameJob> findByLastNameNotContaining(String string);

	List<EmployeeQuery.NameJob> findByLastNameEndingWith(String suffix);

	List<EmployeeQuery.NameJob> findByReportsToIsNull();

	List<EmployeeQuery.NameJob> findByJobTitleNot(String jobTitle);

	List<EmployeeQuery.WithCustomerNameAndPayments> findAllByOrderByCustomers_CustomerNameAscCustomers_Payments_CheckNumber();

	List<EmployeeQuery.WithCustomerNumber> findAllByCustomers_CustomerNumberIsNotNullOrderByEmployeeNumber();

	List<EmployeeQuery.WithCustomerNumber> findAllByCustomers_CustomerNumberIsNullOrderByEmployeeNumber();

	List<EmployeeQuery.WithManager> findAllByOrderByEmployee_LastNameAscEmployee_FirstName();
}
