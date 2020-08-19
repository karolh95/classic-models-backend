package karolh95.classicmodels.criteria.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.CustomerRepositoryCustom;
import karolh95.classicmodels.criteria.specification.CustomerSpecification;
import karolh95.classicmodels.dto.projection.address.StateCity;
import karolh95.classicmodels.dto.projection.customer.Contact;
import karolh95.classicmodels.dto.projection.customer.NameCountryState;
import karolh95.classicmodels.dto.projection.customer.NameCreditLimitCountryState;
import karolh95.classicmodels.dto.projection.customer.NameNumber;
import karolh95.classicmodels.dto.projection.customer.NameSalesRepCountry;
import karolh95.classicmodels.dto.projection.customer.WithOrderNameStatus;
import karolh95.classicmodels.model.Address_;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Customer_;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	public List<Contact> findAllContacts(BiFunction<CriteriaBuilder, Expression<?>, Order> order) {

		CriteriaQuery<Contact> criteria = builder().createQuery(Contact.class);

		Root<Customer> customer = criteria.from(Customer.class);

		Path<String> contactLastName = customer.get(Customer_.CONTACT_LAST_NAME);
		Path<String> contactFirstName = customer.get(Customer_.CONTACT_FIRST_NAME);

		criteria.multiselect(contactLastName, contactFirstName);

		Order byContactLastName = order.apply(builder(), contactLastName);
		Order byContactFirstName = order.apply(builder(), contactFirstName);

		criteria.orderBy(byContactLastName, byContactFirstName);

		TypedQuery<Contact> query = entityManager.createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<NameCountryState> findByCountryState(String country, String state) {

		CriteriaQuery<NameCountryState> query = builder().createQuery(NameCountryState.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> f_name = customer.get(Customer_.CUSTOMER_NAME);
		Path<String> f_country = customer.get(Customer_.ADDRESS).get(Address_.COUNTRY);
		Path<String> f_state = customer.get(Customer_.ADDRESS).get(Address_.STATE);

		Predicate hasCountry =
				CustomerSpecification.hasCountry(country).toPredicate(customer, query, builder());

		Predicate hasState =
				CustomerSpecification.hasState(state).toPredicate(customer, query, builder());

		query.multiselect(f_name, f_country, f_state);

		query.where(hasCountry, hasState);

		TypedQuery<NameCountryState> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	@Override
	public List<NameCreditLimitCountryState> findByCountryStateMinCreditLimit(
			BigDecimal creditLimit, String country, String state) {

		CriteriaQuery<NameCreditLimitCountryState> query =
				builder().createQuery(NameCreditLimitCountryState.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> f_customerName = customer.get(Customer_.CUSTOMER_NAME);
		Path<BigDecimal> f_creditLimit = customer.get(Customer_.CREDIT_LIMIT);
		Path<String> f_country = customer.get(Customer_.ADDRESS).get(Address_.COUNTRY);
		Path<String> f_state = customer.get(Customer_.ADDRESS).get(Address_.STATE);

		Predicate hasCountry =
				CustomerSpecification.hasCountry(country).toPredicate(customer, query, builder());
		Predicate hasState =
				CustomerSpecification.hasState(state).toPredicate(customer, query, builder());
		Predicate minCreditLimit = CustomerSpecification.minCreditLimit(creditLimit)
				.toPredicate(customer, query, builder());

		query.multiselect(f_customerName, f_creditLimit, f_country, f_state);
		query.where(hasCountry, hasState, minCreditLimit);

		TypedQuery<NameCreditLimitCountryState> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	@Override
	public List<NameCreditLimitCountryState> findByMinCreditLimitCountries(BigDecimal creditLimit,
			String... countries) {

		CriteriaQuery<NameCreditLimitCountryState> query =
				builder().createQuery(NameCreditLimitCountryState.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> f_customerName = customer.get(Customer_.CUSTOMER_NAME);
		Path<BigDecimal> f_creditLimit = customer.get(Customer_.CREDIT_LIMIT);
		Path<String> f_country = customer.get(Customer_.ADDRESS).get(Address_.COUNTRY);
		Path<String> f_state = customer.get(Customer_.ADDRESS).get(Address_.STATE);

		Predicate minCreditLimit = CustomerSpecification.minCreditLimit(creditLimit)
				.toPredicate(customer, query, builder());

		Predicate countryIn =
				CustomerSpecification.countryIn(countries).toPredicate(customer, query, builder());

		query.multiselect(f_customerName, f_creditLimit, f_country, f_state);
		query.where(minCreditLimit, countryIn);

		TypedQuery<NameCreditLimitCountryState> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	@Override
	public List<String> findDistinctStates() {

		CriteriaQuery<String> query = builder().createQuery(String.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> state = customer.get(Customer_.ADDRESS).get(Address_.STATE);

		query.select(state);
		query.distinct(true);

		TypedQuery<String> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	@Override
	public List<StateCity> findDistinctStateCity() {

		CriteriaQuery<StateCity> query = builder().createQuery(StateCity.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> state = customer.get(Customer_.ADDRESS).get(Address_.STATE);
		Path<String> city = customer.get(Customer_.ADDRESS).get(Address_.CITY);

		Order byState = builder().asc(state);
		Order byCity = builder().asc(city);

		Predicate stateNotNull =
				CustomerSpecification.stateNotNull().toPredicate(customer, query, builder());

		query.multiselect(state, city);
		query.where(stateNotNull);
		query.orderBy(byState, byCity);
		query.distinct(true);

		TypedQuery<StateCity> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	@Override
	public Long countStatesByCountry(String country) {

		CriteriaQuery<Long> query = builder().createQuery(Long.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> state = customer.get(Customer_.ADDRESS).get(Address_.STATE);

		Expression<Long> countDistinctStates = builder().countDistinct(state);

		Predicate hasCountry =
				CustomerSpecification.hasCountry(country).toPredicate(customer, query, builder());

		query.select(countDistinctStates);
		query.where(hasCountry);

		TypedQuery<Long> typedQuery = entityManager.createQuery(query);

		return typedQuery.getSingleResult();
	}

	@Override
	public List<String> findFirst5DistinctStates() {

		CriteriaQuery<String> query = builder().createQuery(String.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> state = customer.get(Customer_.ADDRESS).get(Address_.STATE);

		Predicate stateNotNull =
				CustomerSpecification.stateNotNull().toPredicate(customer, query, builder());

		query.distinct(true);
		query.select(state);
		query.where(stateNotNull);

		int OFFSET = 0;
		int MAX_RESULTS = 5;

		TypedQuery<String> typedQuery = entityManager.createQuery(query);

		typedQuery.setFirstResult(OFFSET);
		typedQuery.setMaxResults(MAX_RESULTS);

		return typedQuery.getResultList();
	}

	@Override
	public List<NameSalesRepCountry> findSalesRepCountry() {

		CriteriaQuery<NameSalesRepCountry> query = builder().createQuery(NameSalesRepCountry.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> customerName = customer.get(Customer_.CUSTOMER_NAME);
		Path<Long> salesRepEmployeeNumber = customer.get(Customer_.SALES_REP_EMPLOYEE_NUMBER);
		Path<String> country = customer.get(Customer_.ADDRESS).get(Address_.COUNTRY);

		Predicate salesRepEmployeeNumberNotNull = CustomerSpecification
				.salesRepEmployeeNumberNotNull().toPredicate(customer, query, builder());

		Order byCustomerName = builder().asc(customerName);

		query.multiselect(customerName, salesRepEmployeeNumber, country);
		query.where(salesRepEmployeeNumberNotNull);
		query.orderBy(byCustomerName);

		TypedQuery<NameSalesRepCountry> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	@Override
	public List<NameNumber> find(int page, int size) {

		CriteriaQuery<NameNumber> query = builder().createQuery(NameNumber.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<String> customerName = customer.get(Customer_.CUSTOMER_NAME);
		Path<Long> customerNumber = customer.get(Customer_.CUSTOMER_NUMBER);

		Order byCustomerName = builder().asc(customerName);

		query.multiselect(customerName, customerNumber);
		query.orderBy(byCustomerName);

		TypedQuery<NameNumber> typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult((page - 1) * size);
		typedQuery.setMaxResults(size);

		return typedQuery.getResultList();
	}

	@Override
	public List<WithOrderNameStatus> findByOrderNumberNotNull() {

		return findByOrderNumberIs(CustomerSpecification::orderNumberNotNull);
	}

	@Override
	public List<WithOrderNameStatus> findByOrderNumberNull() {

		return findByOrderNumberIs(CustomerSpecification::orderNumberNull);
	}

	private CriteriaBuilder builder() {
		return entityManager.getCriteriaBuilder();
	}

	private List<WithOrderNameStatus> findByOrderNumberIs(
			Function<Join<Customer, karolh95.classicmodels.model.Order>, Specification<Customer>> orderNumberIs) {

		CriteriaQuery<WithOrderNameStatus> query = builder().createQuery(WithOrderNameStatus.class);

		Root<Customer> customer = query.from(Customer.class);

		Path<Long> customerNumber = customer.get(Customer_.CUSTOMER_NUMBER);
		Path<String> customerName = customer.get(Customer_.CUSTOMER_NAME);

		Join<Customer, karolh95.classicmodels.model.Order> orders =
				customer.join(Customer_.ORDERS, JoinType.LEFT);

		Path<Long> orderNumber = orders.get("orderNumber");
		Path<String> orderStatus = orders.get("status");

		Predicate predicateOrderNumberIs =
				orderNumberIs.apply(orders).toPredicate(customer, query, builder());

		query.multiselect(customerNumber, customerName, orderNumber, orderStatus);
		query.where(predicateOrderNumberIs);

		TypedQuery<WithOrderNameStatus> typedQuery = entityManager.createQuery(query);

		return typedQuery.getResultList();
	}
}
