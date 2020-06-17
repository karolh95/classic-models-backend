package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import karolh95.classicmodels.dto.DTOProductline;
import karolh95.classicmodels.model.Productline;

@Mapper(componentModel = "spring")
public interface ProductlineMapper {

	DTOProductline productlineToDto(Productline productline);

	List<DTOProductline> productlinesToDtos(List<Productline> productlines);
}