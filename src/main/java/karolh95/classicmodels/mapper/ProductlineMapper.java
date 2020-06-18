package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.model.Productline;

@Mapper(componentModel = "spring")
public interface ProductlineMapper {

	DtoProductline productlineToDto(Productline productline);

	List<DtoProductline> productlinesToDtos(List<Productline> productlines);
}