package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DTOPayment;
import karolh95.classicmodels.mapper.PaymentMapper;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK;
import karolh95.classicmodels.repository.PaymentRepository;

@SpringBootTest
public class PaymentMapperTests {

	@Autowired
	private PaymentMapper mapper;

	@Autowired
	private PaymentRepository repository;

	@Test
	@DisplayName("Should map Payment to Dto")
	public void paymentToDtoTest() {

		PaymentPK pk = new PaymentPK();
		pk.setCheckNumber("AB661578");
		pk.setCustomerNumber(471L);

		Optional<Payment> optional = this.repository.findById(pk);
		
		assertTrue(optional.isPresent(), "Payment should exist");

		Payment payment = optional.get();
		DTOPayment dto = this.mapper.paymentToDto(payment);

		assertEquals(payment.getCustomer().getCustomerNumber(), dto.getCustomerNumber(), "Customer number should match");
		assertEquals(payment.getPaymentPK().getCheckNumber(), dto.getCheckNumber(), "Check number should match");				
		assertEquals(payment.getPaymentDate(), dto.getPaymentDate(), "Date should match");
		assertEquals(payment.getAmount(), dto.getAmount(), "Amount should match");

	}
}