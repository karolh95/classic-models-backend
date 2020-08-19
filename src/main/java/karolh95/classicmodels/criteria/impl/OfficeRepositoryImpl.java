package karolh95.classicmodels.criteria.impl;

import java.util.List;
import java.util.function.BiFunction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import karolh95.classicmodels.criteria.OfficeRepositoryCustom;
import karolh95.classicmodels.criteria.specification.OfficeSpecification;
import karolh95.classicmodels.dto.projection.office.CodeCityCountryPhone;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Address_;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.model.Office_;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OfficeRepositoryImpl implements OfficeRepositoryCustom {

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	public List<CodeCityCountryPhone> findInCountries(String... countries) {

		return findByOfficePredicate(countries, OfficeSpecification::countriesIn);
	}

	@Override
	public List<CodeCityCountryPhone> findNotInCountries(String... countries) {

		return findByOfficePredicate(countries, OfficeSpecification::countriesNotIn);
	}

	private List<CodeCityCountryPhone> findByOfficePredicate(String[] countries,
			BiFunction<Join<Office, Address>, String[], Specification<Office>> function) {

		CriteriaQuery<CodeCityCountryPhone> query =
				builder().createQuery(CodeCityCountryPhone.class);
		Root<Office> office = query.from(Office.class);

		Path<String> officeCode = office.get(Office_.OFFICE_CODE);
		Join<Office, Address> address = office.join(Office_.ADDRESS);
		Path<String> city = address.get(Address_.CITY);
		Path<String> country = address.get(Address_.COUNTRY);
		Path<String> phone = address.get(Address_.PHONE);

		Predicate predicate =
				function.apply(address, countries).toPredicate(office, query, builder());

		query.multiselect(officeCode, city, country, phone);
		query.where(predicate);

		return entityManager.createQuery(query).getResultList();
	}

	private CriteriaBuilder builder() {
		return entityManager.getCriteriaBuilder();
	}
}
