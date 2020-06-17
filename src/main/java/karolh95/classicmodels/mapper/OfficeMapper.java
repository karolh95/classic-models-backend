package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import karolh95.classicmodels.dto.DTOOffice;
import karolh95.classicmodels.model.Office;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface OfficeMapper {

	DTOOffice officeToDto(Office office);

	List<DTOOffice> officesToDtos(List<Office> offices);
}