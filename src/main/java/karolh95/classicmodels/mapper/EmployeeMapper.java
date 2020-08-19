package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import karolh95.classicmodels.dto.DtoEmployee;
import karolh95.classicmodels.mapper.resolver.EmployeeResolver;
import karolh95.classicmodels.mapper.resolver.OfficeResolver;
import karolh95.classicmodels.model.Employee;
import karolh95.classicmodels.model.Employee_;
import karolh95.classicmodels.model.Office_;

@Mapper(componentModel = "spring", uses = {EmployeeResolver.class, OfficeResolver.class})
public interface EmployeeMapper {

	DtoEmployee employeeToDto(Employee employee);

	List<DtoEmployee> employeesToDtos(List<Employee> employees);

	@Mapping(target = Employee_.EMPLOYEE_NUMBER, ignore = true)
	@Mapping(target = Employee_.EMPLOYEE, source = Employee_.REPORTS_TO)
	@Mapping(target = Employee_.OFFICE, source = Office_.OFFICE_CODE)
	void updateFromDto(DtoEmployee dtoEmployee, @MappingTarget Employee employee);
}
