package karolh95.classicmodels.criteria;

import java.util.List;
import karolh95.classicmodels.dto.projection.employee.NameJob;
import karolh95.classicmodels.dto.projection.employee.NameJobOffice;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNameAndPayments;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNumber;
import karolh95.classicmodels.dto.projection.employee.WithManager;

public interface EmployeeRepositoryCustom {
	List<NameJob> findEmployees();

	List<NameJob> findByLastNameContaining(String text);

	List<NameJobOffice> findByJobOrOffice(String job, String office);

	List<NameJobOffice> findByOfficeCodeBetween(String low, String high);

	List<WithManager> findEmployeesOrderByEmployeeName();

	List<WithCustomerNumber> findEmployeesWithCustomers();

	List<WithCustomerNumber> findEmployeesWithoutCustomers();

	List<WithCustomerNameAndPayments> findEmployeePayments();
}
