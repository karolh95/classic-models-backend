package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import karolh95.classicmodels.dto.DtoProduct;
import karolh95.classicmodels.mapper.resolver.ProductlineResolver;
import karolh95.classicmodels.model.Product;
import karolh95.classicmodels.model.Product_;
import karolh95.classicmodels.model.Productline_;

@Mapper(componentModel = "spring", uses = {ProductlineResolver.class})
public interface ProductMapper {

	@Mapping(target = Productline_.PRODUCTLINE, source = Product_.PRODUCTLINE_ID)
	DtoProduct productToDto(Product product);

	List<DtoProduct> productsToDtos(List<Product> products);

	@Mapping(target = Product_.PRODUCT_CODE, ignore = true)
	void updateFromDto(DtoProduct dtoProduct, @MappingTarget Product product);
}
