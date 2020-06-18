package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoProductline;
import karolh95.classicmodels.mapper.ProductlineMapper;
import karolh95.classicmodels.model.Productline;
import karolh95.classicmodels.repository.ProductlineRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductlineService {

	private final ProductlineMapper mapper;
	private final ProductlineRepository repository;

	public List<DtoProductline> getAllProductlines(){

		List<Productline> productlines = this.repository.findAll();
		return this.mapper.productlinesToDtos(productlines);
	}

	public DtoProductline getProductline(String productline){
		
		Optional<Productline> optional = this.repository.findById(productline);
		if (optional.isEmpty()){
			return null;
		}
		Productline productlineObject = optional.get();
		return this.mapper.productlineToDto(productlineObject);
	}
}