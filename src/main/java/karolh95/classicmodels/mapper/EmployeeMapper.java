package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DTOEmployee;
import karolh95.classicmodels.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	@Mapping(target = "reportsTo", source = "employee.employeeNumber")
	@Mapping(target = "officeCode", source = "office.officeCode")
	DTOEmployee employeeToDto(Employee employee);

	List<DTOEmployee> employeesToDtos(List<Employee> employees);
}