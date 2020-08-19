package karolh95.classicmodels.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK_;
import karolh95.classicmodels.model.Payment_;

@Mapper(componentModel = "spring", uses = {CustomerResolver.class})
public interface PaymentMapper {

	String PK_CUSTOMER_NUMBER = Payment_.PAYMENT_PK + "." + PaymentPK_.CUSTOMER_NUMBER;
	String PK_CHECK_NUMBER = Payment_.PAYMENT_PK + "." + PaymentPK_.CHECK_NUMBER;

	DtoPayment paymentToDto(Payment payment);

	List<DtoPayment> paymentsToDtos(List<Payment> payments);

	@Mapping(target = Payment_.CUSTOMER, source = Payment_.CUSTOMER_NUMBER)
	@Mapping(target = PK_CUSTOMER_NUMBER, source = Payment_.CUSTOMER_NUMBER)
	@Mapping(target = PK_CHECK_NUMBER, source = Payment_.CHECK_NUMBER)
	Payment paymentFromDto(DtoPayment dtoPayment);
}
