package karolh95.classicmodels.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.dto.query.AddressQuery;
import karolh95.classicmodels.dto.query.CustomerContact;
import karolh95.classicmodels.dto.query.CustomerCreditLimit;
import karolh95.classicmodels.dto.query.CustomerDetail;
import karolh95.classicmodels.dto.query.CustomerFullDetail;
import karolh95.classicmodels.dto.query.CustomerSalesRep;
import karolh95.classicmodels.dto.query.CustomerSummary;
import karolh95.classicmodels.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<CustomerContact> findAllByOrderByContactLastName();

	List<CustomerContact> findAllByOrderByContactLastNameDesc();

	List<CustomerContact> findAllBy(Sort sort);

	List<CustomerDetail> findByAddress_CountryAndAddress_State(String country, String state);

	List<CustomerFullDetail> findByAddress_CountryAndAddress_StateAndCreditLimitGreaterThan(String country,
			String state, BigDecimal creditlimit);

	List<CustomerFullDetail> findByCreditLimitGreaterThanAndAddress_CountryIn(BigDecimal creditLimit,
			String... strings);

	List<CustomerSummary> findAllBy(Pageable pageRequest);

	List<CustomerCreditLimit> findBy(Pageable pageRequest);

	List<AddressQuery.State> findDistinctAddress_StateBy();

	List<AddressStateCity> findDistinctByAddress_StateNotNull(Sort sort);

	List<AddressQuery.State> findDistinctAddress_StateByAddress_Country(String country);

	List<AddressQuery.State> findFirst5DistinctAddress_StateByAddress_StateNotNull();

	List<CustomerSalesRep> findBySalesRepEmployeeNumberIsNotNull(Sort sort);
}