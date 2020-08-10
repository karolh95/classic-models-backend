package karolh95.classicmodels.criteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import karolh95.classicmodels.dto.projection.address.StateCity;
import karolh95.classicmodels.dto.projection.customer.Contact;
import karolh95.classicmodels.dto.projection.customer.NameCountryState;
import karolh95.classicmodels.dto.projection.customer.NameCreditLimitCountryState;
import karolh95.classicmodels.dto.projection.customer.NameNumber;
import karolh95.classicmodels.dto.projection.customer.NameSalesRepCountry;
import karolh95.classicmodels.dto.projection.customer.WithOrderNameStatus;

public interface CustomerRepositoryCustom {

	List<Contact> findAllContacts(BiFunction<CriteriaBuilder, Expression<?>, Order> order);

	List<NameCountryState> findByCountryState(String country, String state);

	List<NameCreditLimitCountryState> findByCountryStateMinCreditLimit(BigDecimal creditLimit,
			String country, String state);

	List<NameCreditLimitCountryState> findByMinCreditLimitCountries(BigDecimal creditLimit,
			String... countries);

	List<String> findDistinctStates();

	List<String> findFirst5DistinctStates();

	List<StateCity> findDistinctStateCity();

	Long countStatesByCountry(String country);

	List<NameSalesRepCountry> findSalesRepCountry();

	List<NameNumber> find(int page, int size);

	List<WithOrderNameStatus> findByOrderNumberNotNull();

	List<WithOrderNameStatus> findByOrderNumberNull();
}
