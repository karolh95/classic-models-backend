package karolh95.classicmodels.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.AddressQuery;
import karolh95.classicmodels.dto.query.CustomerQuery;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.service.NthByField.Find;

@Repository
public interface CustomerRepository
		extends JpaRepository<Customer, Long>, Find<CustomerQuery.NameCreditLimit> {

	List<CustomerQuery.Contact> findAllByOrderByContactLastName();

	List<CustomerQuery.Contact> findAllByOrderByContactLastNameDesc();

	List<CustomerQuery.Contact> findAllBy(Sort sort);

	List<CustomerQuery.NameCountryState> findByAddress_CountryAndAddress_State(String country,
			String state);

	List<CustomerQuery.NameCreditLimitCountryState> findByAddress_CountryAndAddress_StateAndCreditLimitGreaterThan(
			String country, String state, BigDecimal creditlimit);

	List<CustomerQuery.NameCreditLimitCountryState> findByCreditLimitGreaterThanAndAddress_CountryIn(
			BigDecimal creditLimit, String... strings);

	List<CustomerQuery.NameNumber> findAllBy(Pageable pageRequest);

	List<AddressQuery.State> findDistinctAddress_StateBy();

	List<AddressQuery.StateCity> findDistinctByAddress_StateNotNull(Sort sort);

	List<AddressQuery.State> findDistinctAddress_StateByAddress_Country(String country);

	List<AddressQuery.State> findFirst5DistinctAddress_StateByAddress_StateNotNull();

	List<CustomerQuery.NameSalesRepCountry> findBySalesRepEmployeeNumberIsNotNull(Sort sort);

	List<CustomerQuery.WithOrderNameStatus> findAllByOrders_OrderNumberIsNotNull();

	List<CustomerQuery.WithOrderNameStatus> findAllByOrders_OrderNumberIsNull();
}
