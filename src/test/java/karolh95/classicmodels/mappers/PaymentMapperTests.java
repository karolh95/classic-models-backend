package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.mapper.PaymentMapper;
import karolh95.classicmodels.mapper.PaymentMapperImpl;
import karolh95.classicmodels.mapper.resolver.CustomerResolver;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.utils.CustomerUtil;
import karolh95.classicmodels.utils.PaymentUtil;

class PaymentMapperTests {

	@Mock
	CustomerResolver customerResolver;

	@InjectMocks
	PaymentMapper mapper = new PaymentMapperImpl();

	@Nested
	@DisplayName("paymentToDto tests")
	class PaymentToDtoTests {

		@Test
		@DisplayName("Should not map null Payment to dto")
		void mapNullPaymentToDto() {

			Payment payment = null;

			DtoPayment dtoPayment = mapper.paymentToDto(payment);

			assertNull(dtoPayment, "Dto payment should be null");
		}

		@Test
		@DisplayName("Should map Payment to Dto")
		void paymentToDtoTest() {

			Payment payment = PaymentUtil.payment();

			DtoPayment dtoPayment = mapper.paymentToDto(payment);

			PaymentUtil.assertEquals(payment, dtoPayment);
		}
	}

	@Nested
	@DisplayName("paymentsToDtos tests")
	class PaymentsToDtosTests {

		List<Payment> payments;
		List<DtoPayment> dtoPayments;

		@BeforeEach
		void init() {

			payments = PaymentUtil.payments();
		}

		@Test
		@DisplayName("Should not map null payments to dtos")
		void mapNullPauments() {

			payments = null;

			dtoPayments = mapper.paymentsToDtos(payments);

			assertNull(dtoPayments, "Dto payments should be null");
		}

		@Test
		@DisplayName("Should map empty payments to dtos")
		void mapEmptyPayments() {

			payments.clear();

			dtoPayments = mapper.paymentsToDtos(payments);

			assertTrue(dtoPayments.isEmpty(), "Dto payments should be null");
		}

		@Test
		@DisplayName("Should map payments to dtos")
		void mapPaymentsToDtos() {

			dtoPayments = mapper.paymentsToDtos(payments);

			for (int i = 0; i < payments.size(); i++) {

				Payment payment = payments.get(i);
				DtoPayment dtoPayment = dtoPayments.get(i);

				PaymentUtil.assertEquals(payment, dtoPayment);
			}
		}
	}

	@Nested
	@DisplayName("paymentFromDto tests")
	class PaymentFromDtoTests {

		@BeforeEach
		void init() {

			MockitoAnnotations.initMocks(PaymentMapperTests.this);

			when(customerResolver.map(anyLong())).thenReturn(CustomerUtil.customer());
		}

		@Test
		@DisplayName("Should not map from null dto")
		void mapPaymentFromNullDto() {

			DtoPayment dtoPayment = null;

			Payment payment = mapper.paymentFromDto(dtoPayment);

			assertNull(payment, "Payment should be null");

		}

		@Test
		@DisplayName("Should map payment from dto")
		void mapPaymentFromDto() {

			DtoPayment dtoPayment = PaymentUtil.dtoPayment();

			Payment payment = mapper.paymentFromDto(dtoPayment);

			PaymentUtil.assertEquals(payment, dtoPayment);
		}
	}

}