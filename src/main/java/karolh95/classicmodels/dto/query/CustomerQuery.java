package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class CustomerQuery {

	public static interface Contact {
		String getContactFirstName();

		String getContactLastName();
	}

	public static interface Number {
		Long getCustomerNumber();
	}

	public static interface SalesRep {
		Long getSalesRepEmployeeNumber();
	}

	public static interface Name {
		String getCustomerName();
	}

	public static interface CreditLimit {
		BigDecimal getCreditLimit();
	}

	public static interface NameNumber extends Number, Name {

	}

	public static interface NameCreditLimit extends Name, CreditLimit {

	}

	public static interface NameSalesRepCountry extends Name, SalesRep, AddressQuery.Country {

	}

	public static interface NameCountryState extends Name, AddressQuery.Country, AddressQuery.State {

	}

	public static interface NameCreditLimitCountryState extends NameCountryState, CreditLimit {

	}

}