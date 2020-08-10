package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class CustomerQuery extends AddressQuery {

	public interface Number {
		Long getCustomerNumber();
	}

	public interface Name {
		String getCustomerName();
	}

	public interface CreditLimit {
		BigDecimal getCreditLimit();
	}

	public interface NameCreditLimit extends Name, CreditLimit {
	}
}
