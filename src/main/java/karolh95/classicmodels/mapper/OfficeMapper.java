package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.model.Office;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface OfficeMapper {

	DtoOffice officeToDto(Office office);

	List<DtoOffice> officesToDtos(List<Office> offices);

	@Mapping(target = "officeCode", ignore = true)
	void updateFromDto(DtoOffice dtoOffice, @MappingTarget Office office);
}