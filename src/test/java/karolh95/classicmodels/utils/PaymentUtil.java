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

public final class PaymentUtil {

	private static final Long NOW = System.currentTimeMillis();
	private static final BigDecimal AMOUNT = new BigDecimal("25.99");
	private static final Date PAYMENT_DATE = new Date(NOW);
	private static final int CHECK_NUMBER_MIN = 1;
	private static final int CHECK_NUMBER_MAX = 6;

	private PaymentUtil() {

	}

	public static Payment payment() {
		Customer customer = customer();
		return payment(customer, CHECK_NUMBER_MIN);
	}

	private static Payment payment(Customer customer, int checkNumber) {

		Payment payment = new Payment();

		PaymentPK pk = new PaymentPK(customer.getCustomerNumber(), String.valueOf(checkNumber));

		payment.setPaymentPK(pk);
		payment.setCustomerNumber(pk.getCustomerNumber());
		payment.setCheckNumber(pk.getCheckNumber());
		payment.setAmount(AMOUNT);
		payment.setPaymentDate(PAYMENT_DATE);

		payment.setCustomer(customer);

		return payment;
	}

	private static Customer customer() {

		Customer customer = new Customer();

		customer.setCustomerNumber(CustomerUtil.customer().getCustomerNumber());

		return customer;
	}

	public static DtoPayment dtoPayment() {

		DtoPayment payment = new DtoPayment();

		payment.setCheckNumber(String.valueOf(CHECK_NUMBER_MIN));
		payment.setCustomerNumber(customer().getCustomerNumber());
		payment.setAmount(AMOUNT);
		payment.setPaymentDate(PAYMENT_DATE);

		return payment;
	}

	public static List<Payment> payments() {

		List<Payment> payments = new ArrayList<>();
		Customer customer = customer();

		for (int checkNumber = CHECK_NUMBER_MIN; checkNumber < CHECK_NUMBER_MAX; checkNumber++) {
			payments.add(payment(customer, checkNumber));
		}

		return payments;
	}

	public static void assertEquals(Payment payment, DtoPayment dtoPayment) {

		assertNotNull(payment, "Payment should not be null");
		assertNotNull(dtoPayment, "DTO payment should not be null");

		assertPaymentsPKsEquals(payment, dtoPayment);
		assertModifiabkeFieldsEquals(payment, dtoPayment);
		assertCustomersNumbersEquals(payment.getCustomer(), dtoPayment.getCustomerNumber());
	}

	private static void assertPaymentsPKsEquals(Payment payment, DtoPayment dtoPayment) {

		PaymentPK pk = payment.getPaymentPK();

		assertNotNull(pk, "Payment PK should not be null");
		Assertions.assertEquals(pk.getCheckNumber(), dtoPayment.getCheckNumber(),
				"Check number should match");
		Assertions.assertEquals(pk.getCustomerNumber(), dtoPayment.getCustomerNumber());
	}

	private static void assertModifiabkeFieldsEquals(Payment payment, DtoPayment dtoPayment) {

		Assertions.assertEquals(payment.getAmount(), dtoPayment.getAmount(), "Amount should match");
		Assertions.assertEquals(payment.getPaymentDate(), dtoPayment.getPaymentDate(),
				"Date should match");
	}

	private static void assertCustomersNumbersEquals(Customer customer, Long customerNumber) {

		assertNotNull(customer, "Customer should not be null");
		Assertions.assertEquals(customer.getCustomerNumber(), customerNumber,
				"Customer number should match");
	}
}
