package karolh95.classicmodels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Component;

import karolh95.classicmodels.dto.query.CustomerQuery.NameCreditLimit;
import karolh95.classicmodels.repository.CustomerRepository;
import lombok.Getter;

@Getter
@Component
public class NthCustomerByCreditLimit extends NthByField<NameCreditLimit> {

	@Autowired
	private CustomerRepository repository;

	private Sort sortMethod;

	public NthCustomerByCreditLimit() {

		TypedSort<NameCreditLimit> sort = Sort.sort(NameCreditLimit.class);
		sortMethod = sort.by(NameCreditLimit::getCreditLimit);
	}
}