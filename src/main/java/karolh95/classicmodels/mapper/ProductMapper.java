package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.mapper.resolver.ProductlineResolver;
import karolh95.classicmodels.model.Product;

@Mapper(componentModel = "spring", uses = { ProductlineResolver.class })
public interface ProductMapper {

	@Mapping(target = "productline", source = "productline.productline")
	DtoProduct productToDto(Product product);

	List<DtoProduct> productsToDtos(List<Product> products);

	@Mapping(target = "productCode", ignore = true)
	void updateFromDto(DtoProduct dtoProduct, @MappingTarget Product product);
}