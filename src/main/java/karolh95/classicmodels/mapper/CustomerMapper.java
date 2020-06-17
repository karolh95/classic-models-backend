package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DTOCustomer;
import karolh95.classicmodels.model.Customer;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface CustomerMapper {

	@Mapping(target = "salesRepEmployeeNumber", source = "employee.employeeNumber")
	DTOCustomer customerToDto(Customer customer);

	List<DTOCustomer> customersToDtos(List<Customer> customers);
}