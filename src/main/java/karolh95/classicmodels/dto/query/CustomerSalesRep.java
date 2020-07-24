package karolh95.classicmodels.dto.query;

public interface CustomerSalesRep extends AddressQuery.Country {

	String getCustomerName();

	Long getSalesRepEmployeeNumber();
}