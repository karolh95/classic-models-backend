package karolh95.classicmodels.dto.query;

public interface OfficeDetail extends AddressQuery.City, AddressQuery.Country, AddressQuery.Phone {

	String getOfficeCode();
}