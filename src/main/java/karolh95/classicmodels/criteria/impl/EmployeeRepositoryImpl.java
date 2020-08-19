package karolh95.classicmodels.criteria.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.EmployeeRepositoryCustom;
import karolh95.classicmodels.criteria.specification.EmployeeSpecification;
import karolh95.classicmodels.dto.projection.employee.NameJob;
import karolh95.classicmodels.dto.projection.employee.NameJobOffice;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNameAndPayments;
import karolh95.classicmodels.dto.projection.employee.WithCustomerNumber;
import karolh95.classicmodels.dto.projection.employee.WithManager;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Customer_;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Employee_;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.Payment_;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	public List<NameJob> findEmployees() {

		CriteriaQuery<NameJob> query = builder().createQuery(NameJob.class);
		Root<Employee> employee = query.from(Employee.class);

		Path<String> jobTitle = employee.get(Employee_.JOB_TITLE);

		Expression<String> fullName = fullName(employee);

		query.multiselect(fullName.alias("name"), jobTitle);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<NameJob> findByLastNameContaining(String text) {

		CriteriaQuery<NameJob> query = builder().createQuery(NameJob.class);
		Root<Employee> employee = query.from(Employee.class);

		Path<String> jobTitle = employee.get(Employee_.JOB_TITLE);

		Predicate lastNameContaining = EmployeeSpecification.lastNameContaining(text)
				.toPredicate(employee, query, builder());

		Expression<String> fullName = fullName(employee);

		query.multiselect(fullName.alias("name"), jobTitle);
		query.where(lastNameContaining);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<NameJobOffice> findByJobOrOffice(String job, String office) {

		CriteriaQuery<NameJobOffice> query = builder().createQuery(NameJobOffice.class);
		Root<Employee> employee = query.from(Employee.class);

		Path<String> jobTitle = employee.get(Employee_.JOB_TITLE);
		Path<String> officeCode = employee.get(Employee_.OFFICE_CODE);

		Expression<String> fullName = fullName(employee);

		Predicate hasJobTitle =
				EmployeeSpecification.hasJob(job).toPredicate(employee, query, builder());

		Predicate hasOffice =
				EmployeeSpecification.hasOffice(office).toPredicate(employee, query, builder());

		Predicate jobTitleOrOfficeCode = builder().or(hasJobTitle, hasOffice);

		Order byOfficeCode = builder().asc(officeCode);
		Order byJobTitle = builder().asc(jobTitle);

		query.multiselect(fullName, jobTitle, officeCode);
		query.where(jobTitleOrOfficeCode);
		query.orderBy(byOfficeCode, byJobTitle);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<NameJobOffice> findByOfficeCodeBetween(String low, String high) {

		CriteriaQuery<NameJobOffice> query = builder().createQuery(NameJobOffice.class);
		Root<Employee> employee = query.from(Employee.class);

		Path<String> lastName = employee.get(Employee_.LAST_NAME);
		Path<String> jobTitle = employee.get(Employee_.JOB_TITLE);
		Path<String> officeCode = employee.get(Employee_.OFFICE_CODE);

		Expression<String> fullName = fullName(employee);

		Predicate officeCodeBetween = EmployeeSpecification.officeCodeBetween(low, high)
				.toPredicate(employee, query, builder());

		Order byOfficeCode = builder().asc(officeCode);
		Order byLastName = builder().asc(lastName);

		query.multiselect(fullName, jobTitle, officeCode);
		query.where(officeCodeBetween);
		query.orderBy(byOfficeCode, byLastName);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithManager> findEmployeesOrderByEmployeeName() {

		CriteriaQuery<WithManager> query = builder().createQuery(WithManager.class);
		Root<Employee> employee = query.from(Employee.class);

		Join<Employee, Employee> reporter = employee.join(Employee_.EMPLOYEES, JoinType.LEFT);

		Expression<String> fullName = fullName(employee);
		Expression<String> reporterFullName = fullName(reporter);

		query.multiselect(fullName.alias("manager"), reporterFullName.alias("reporter"));

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithCustomerNumber> findEmployeesWithCustomers() {

		CriteriaQuery<WithCustomerNumber> query = builder().createQuery(WithCustomerNumber.class);
		Root<Employee> employee = query.from(Employee.class);

		Expression<String> fullName = fullName(employee);
		Join<Employee, Customer> customers = employee.join(Employee_.CUSTOMERS, JoinType.INNER);
		Path<Long> customerNumber = customers.get(Customer_.CUSTOMER_NUMBER);

		Predicate customerNumberNotNull = EmployeeSpecification.customerNumberIsNotNull(customers)
				.toPredicate(employee, query, builder());
		Order byEmployeeNumber = builder().asc(employee.get(Employee_.EMPLOYEE_NUMBER));

		query.multiselect(fullName.alias("name"), customerNumber);
		query.where(customerNumberNotNull);
		query.orderBy(byEmployeeNumber);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithCustomerNumber> findEmployeesWithoutCustomers() {

		CriteriaQuery<WithCustomerNumber> query = builder().createQuery(WithCustomerNumber.class);
		Root<Employee> employee = query.from(Employee.class);

		Expression<String> fullName = fullName(employee);
		Join<Employee, Customer> customer = employee.join(Employee_.CUSTOMERS, JoinType.LEFT);
		Path<Long> customerNumber = customer.get(Customer_.CUSTOMER_NUMBER);

		Predicate customerNumberIsNull = EmployeeSpecification.customerNumberIsNull(customer)
				.toPredicate(employee, query, builder());

		Order byEmployeeNumber = builder().asc(employee.get(Employee_.EMPLOYEE_NUMBER));

		query.multiselect(fullName.alias("name"), customerNumber);
		query.where(customerNumberIsNull);
		query.orderBy(byEmployeeNumber);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<WithCustomerNameAndPayments> findEmployeePayments() {

		CriteriaQuery<WithCustomerNameAndPayments> query =
				builder().createQuery(WithCustomerNameAndPayments.class);
		Root<Employee> employee = query.from(Employee.class);

		Expression<String> fullName = fullName(employee);

		Join<Employee, Customer> customer = employee.join(Employee_.CUSTOMERS, JoinType.LEFT);
		Path<String> customerName = customer.get(Customer_.CUSTOMER_NAME);

		Join<Customer, Payment> payment = customer.join(Customer_.PAYMENTS, JoinType.LEFT);
		Path<String> checkNumber = payment.get(Payment_.CHECK_NUMBER);
		Path<BigDecimal> amount = payment.get(Payment_.AMOUNT);

		Order byCustomerName = builder().asc(customerName);
		Order byCheckNumber = builder().asc(checkNumber);

		query.multiselect(fullName, customerName, checkNumber, amount);
		query.orderBy(byCustomerName, byCheckNumber);

		return entityManager.createQuery(query).getResultList();
	}

	private CriteriaBuilder builder() {
		return entityManager.getCriteriaBuilder();
	}

	private Expression<String> fullName(Path<Employee> employee) {

		Path<String> lastName = employee.get(Employee_.LAST_NAME);
		Path<String> firstName = employee.get(Employee_.FIRST_NAME);

		Expression<String> fullName = builder().concat(lastName, ", ");

		return builder().concat(fullName, firstName);
	}
}
