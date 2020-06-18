package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	@Mapping(target = "reportsTo", source = "employee.employeeNumber")
	@Mapping(target = "officeCode", source = "office.officeCode")
	DtoEmployee employeeToDto(Employee employee);

	List<DtoEmployee> employeesToDtos(List<Employee> employees);
}