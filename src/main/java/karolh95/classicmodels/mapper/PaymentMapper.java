package karolh95.classicmodels.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

	@Mapping(target = "customerNumber", source = "paymentPK.customerNumber")
	@Mapping(target = "checkNumber", source = "paymentPK.checkNumber")
	DtoPayment paymentToDto(Payment payment);

	List<DtoPayment> paymentsToDtos(List<Payment> payments);
}