package karolh95.classicmodels.criteria.specification;

import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Customer_;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Employee_;

public class EmployeeSpecification {

	public static Specification<Employee> lastNameContaining(String text) {

		return (employee, query, builder) -> builder.like(employee.get(Employee_.LAST_NAME),
				"%" + text + "%");
	}

	public static Specification<Employee> hasJob(String jobTitle) {

		return (employee, query, builder) -> builder.equal(employee.get(Employee_.JOB_TITLE),
				jobTitle);
	}

	public static Specification<Employee> hasOffice(String officeCode) {

		return (employee, query, builder) -> builder.equal(employee.get(Employee_.OFFICE_CODE),
				officeCode);
	}

	public static Specification<Employee> officeCodeBetween(String low, String high) {

		return (employee, query, builder) -> builder.between(employee.get(Employee_.OFFICE_CODE),
				low, high);
	}

	public static Specification<Employee> customerNumberIsNotNull(
			Join<Employee, Customer> customers) {

		return (employee, query, builder) -> builder
				.isNotNull(customers.get(Customer_.CUSTOMER_NUMBER));
	}

	public static Specification<Employee> customerNumberIsNull(Join<Employee, Customer> customers) {

		return (employee, query, builder) -> builder
				.isNull(customers.get(Customer_.CUSTOMER_NUMBER));
	}
}
