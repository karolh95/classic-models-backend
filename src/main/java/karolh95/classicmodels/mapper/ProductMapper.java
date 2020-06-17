package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DTOProduct;
import karolh95.classicmodels.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(target = "productline", source = "productline.productline")
	DTOProduct productToDto(Product product);

	List<DTOProduct> productsToDtos(List<Product> products);
}