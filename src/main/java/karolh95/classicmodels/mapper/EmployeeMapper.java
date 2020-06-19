package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.mapper.resolver.OfficeResolver;
import karolh95.classicmodels.model.Employee;

@Mapper(componentModel = "spring", uses = { EmployeeResolver.class, OfficeResolver.class })
public interface EmployeeMapper {

	@Mapping(target = "reportsTo", source = "employee.employeeNumber")
	@Mapping(target = "officeCode", source = "office.officeCode")
	DtoEmployee employeeToDto(Employee employee);

	List<DtoEmployee> employeesToDtos(List<Employee> employees);

	@Mapping(target = "employeeNumber", ignore = true)
	@Mapping(target = "employee", source = "reportsTo")
	@Mapping(target = "office", source = "officeCode")
	void updateFromDto(DtoEmployee dtoEmployee, @MappingTarget Employee employee);
}