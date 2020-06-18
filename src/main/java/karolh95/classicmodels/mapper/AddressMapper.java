package karolh95.classicmodels.mapper;

import org.mapstruct.Mapper;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	DtoAddress addressToDto(Address address);
}