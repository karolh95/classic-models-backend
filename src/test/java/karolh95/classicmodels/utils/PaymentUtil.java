package karolh95.classicmodels.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK;

public class PaymentUtil {

	private static final Long NOW = System.currentTimeMillis();

	public static Payment payment() {

		Payment payment = new Payment();

		String checkNumber = "checkNumber";
		Customer customer = CustomerUtil.customer();

		PaymentPK pk = new PaymentPK(customer.getCustomerNumber(), checkNumber);

		payment.setPaymentPK(pk);
		payment.setAmount(new BigDecimal("25.99"));
		payment.setPaymentDate(new Date(NOW));

		payment.setCustomer(customer);

		return payment;
	}

	public static DtoPayment dtoPayment() {

		DtoPayment payment = new DtoPayment();

		payment.setCheckNumber("checkNumber");
		payment.setCustomerNumber(CustomerUtil.customer().getCustomerNumber());
		payment.setAmount(new BigDecimal("25.99"));
		payment.setPaymentDate(new Date(NOW));

		return payment;
	}

	public static List<Payment> payments() {

		List<Payment> payments = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			payments.add(payment());
		}

		return payments;
	}

	public static void assertEquals(Payment payment, DtoPayment dtoPayment) {

		assertNotNull(payment, "Payment should not be null");
		assertNotNull(dtoPayment, "DTO payment should not be null");

		Assertions.assertEquals(payment.getAmount(), dtoPayment.getAmount(), "Amount should match");
		Assertions.assertEquals(payment.getPaymentDate(), dtoPayment.getPaymentDate(), "Date should match");

		Customer customer = payment.getCustomer();

		assertNotNull(customer, "Customer should not be null");
		Assertions.assertEquals(customer.getCustomerNumber(), dtoPayment.getCustomerNumber(),
				"Customer number should match");

		PaymentPK pk = payment.getPaymentPK();

		assertNotNull(pk, "Payment PK should not be null");
		Assertions.assertEquals(pk.getCheckNumber(), dtoPayment.getCheckNumber(), "Check number should match");
		Assertions.assertEquals(pk.getCustomerNumber(), dtoPayment.getCustomerNumber());
	}
}