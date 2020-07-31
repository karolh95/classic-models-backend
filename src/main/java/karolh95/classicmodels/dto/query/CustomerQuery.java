package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class CustomerQuery extends AddressQuery {

	public interface Number {
		Long getCustomerNumber();
	}

	public interface Name {
		String getCustomerName();
	}

	public interface Contact {
		String getContactFirstName();

		String getContactLastName();
	}

	public interface PostalCode {
		String getPostalCode();
	}

	public interface CreditLimit {
		BigDecimal getCreditLimit();
	}

	public interface SalesRep {
		Long getSalesRepEmployeeNumber();
	}

	public interface NameNumber extends Number, Name {
	}

	public interface NameCreditLimit extends Name, CreditLimit {
	}

	public interface NameSalesRepCountry extends Name, SalesRep, Country {
	}

	public interface NameCountryState extends Name, Country, State {
	}

	public interface NameCreditLimitCountryState extends NameCountryState, CreditLimit {
	}

	public interface WithOrderNameStatus extends Number, Name {
		Long getOrders_OrderNumber();

		String getOrders_Status();
	}
}
