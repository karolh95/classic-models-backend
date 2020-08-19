package karolh95.classicmodels.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import karolh95.classicmodels.dto.DtoCustomer;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Customer_;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, EmployeeResolver.class})
public interface CustomerMapper {

	DtoCustomer customerToDto(Customer customer);

	List<DtoCustomer> customersToDtos(List<Customer> customers);

	@Mapping(target = Customer_.CUSTOMER_NUMBER, ignore = true)
	@Mapping(target = Customer_.EMPLOYEE, source = Customer_.SALES_REP_EMPLOYEE_NUMBER)
	void updateFromDto(DtoCustomer dtoCustomer, @MappingTarget Customer customer);
}
