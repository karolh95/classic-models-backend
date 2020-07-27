package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class CustomerQuery {

	public interface Contact {
		String getContactFirstName();

		String getContactLastName();
	}

	public interface Number {
		Long getCustomerNumber();
	}

	public interface SalesRep {
		Long getSalesRepEmployeeNumber();
	}

	public interface Name {
		String getCustomerName();
	}

	public interface CreditLimit {
		BigDecimal getCreditLimit();
	}

	public interface NameNumber extends Number, Name {

	}

	public interface NameCreditLimit extends Name, CreditLimit {

	}

	public interface NameSalesRepCountry extends Name, SalesRep, AddressQuery.Country {

	}

	public interface NameCountryState extends Name, AddressQuery.Country, AddressQuery.State {

	}

	public interface NameCreditLimitCountryState extends NameCountryState, CreditLimit {

	}

}