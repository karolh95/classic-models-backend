package karolh95.classicmodels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.mapper.OfficeMapper;
import karolh95.classicmodels.model.Office;
import karolh95.classicmodels.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficeService {

	private final OfficeMapper mapper;
	private final OfficeRepository repository;

	public List<DtoOffice> getAllOffices() {

		List<Office> offices = repository.findAll();
		return mapper.officesToDtos(offices);
	}

	public DtoOffice getOffice(String officeCode) {

		Optional<Office> optional = repository.findById(officeCode);

		if (optional.isEmpty()) {
			return null;
		}

		Office office = optional.get();
		return mapper.officeToDto(office);
	}

	public DtoOffice saveOffice(DtoOffice dtoOffice) {

		if (dtoOffice == null || !dtoOffice.isValid()) {
			return null;
		}

		Office office = getOne(dtoOffice.getOfficeCode());

		mapper.updateFromDto(dtoOffice, office);

		office = repository.save(office);

		return mapper.officeToDto(office);

	}

	private Office getOne(String officeCode) {

		Optional<Office> optional = repository.findById(officeCode);

		if (optional.isPresent()) {

			return optional.get();
		} else {

			Office office = new Office();

			office.setOfficeCode(officeCode);

			return office;

		}
	}
}