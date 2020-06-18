package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.model.Customer;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface CustomerMapper {

	@Mapping(target = "salesRepEmployeeNumber", source = "employee.employeeNumber")
	DtoCustomer customerToDto(Customer customer);

	List<DtoCustomer> customersToDtos(List<Customer> customers);
}