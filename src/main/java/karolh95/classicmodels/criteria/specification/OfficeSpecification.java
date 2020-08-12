package karolh95.classicmodels.criteria.specification;

import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Address_;
import karolh95.classicmodels.model.Office;

public class OfficeSpecification {

	public static Specification<Office> countriesIn(Join<Office, Address> address,
			String[] countries) {

		return (office, query, builder) -> address.get(Address_.COUNTRY).in((Object[]) countries);

	}

	public static Specification<Office> countriesNotIn(Join<Office, Address> address,
			String[] countries) {

		return (office, query, builder) -> builder
				.not(address.get(Address_.COUNTRY).in((Object[]) countries));
	}
}
