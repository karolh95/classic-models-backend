package karolh95.classicmodels.criteria.specification;

import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import karolh95.classicmodels.model.Address_;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Customer_;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.Order_;

public class CustomerSpecification {

	public static Specification<Customer> hasNumber(Long customerNumber) {

		return (customer, query, builder) -> builder.equal(customer.get(Customer_.CUSTOMER_NUMBER),
				customerNumber);
	}

	public static Specification<Customer> hasCountry(String country) {

		return (customer, query, builder) -> builder
				.equal(customer.get(Customer_.ADDRESS).get(Address_.COUNTRY), country);
	}

	public static Specification<Customer> hasState(String state) {

		return (customer, query, builder) -> builder
				.equal(customer.get(Customer_.ADDRESS).get(Address_.STATE), state);
	}

	public static Specification<Customer> minCreditLimit(BigDecimal creditLimit) {

		return (customer, query, builder) -> builder
				.greaterThan(customer.get(Customer_.CREDIT_LIMIT), creditLimit);
	}

	public static Specification<Customer> countryIn(String[] countries) {

		return (customer, query, builder) -> {
			In<String> countryIn =
					builder.in(customer.get(Customer_.ADDRESS).get(Address_.COUNTRY));
			for (String country : countries) {
				countryIn.value(country);
			}
			return countryIn;
		};
	}

	public static Specification<Customer> stateNotNull() {

		return (customer, query, builder) -> builder
				.isNotNull(customer.get(Customer_.ADDRESS).get(Address_.STATE));
	}

	public static Specification<Customer> salesRepEmployeeNumberNotNull() {

		return (customer, query, builder) -> builder
				.isNotNull(customer.get(Customer_.SALES_REP_EMPLOYEE_NUMBER));
	}

	public static Specification<Customer> orderNumberNotNull(Join<Customer, Order> orders) {

		return (customer, query, builder) -> builder.isNotNull(orders.get(Order_.CUSTOMER_NUMBER));
	}

	public static Specification<Customer> orderNumberNull(Join<Customer, Order> orders) {

		return (customer, query, builder) -> builder.isNull(orders.get(Order_.CUSTOMER_NUMBER));
	}
}
