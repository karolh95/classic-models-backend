package karolh95.classicmodels.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.mapper.PaymentMapper;
import karolh95.classicmodels.model.Order;
import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK;
import karolh95.classicmodels.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentMapper mapper;
	private final PaymentRepository repository;

	public List<DtoPayment> getAllPayments() {

		List<Payment> payments = repository.findAll();
		return mapper.paymentsToDtos(payments);
	}

	public List<DtoPayment> getPayments(Long customerNumber) {

		List<Payment> payments = getPaymentsByCustomerNumber(customerNumber);
		return mapper.paymentsToDtos(payments);
	}

	private List<Payment> getPaymentsByCustomerNumber(Long customerNumber) {

		return repository.findByPaymentPKCustomerNumber(customerNumber);
	}

	public DtoPayment getPayment(Long customerNumber, String checkNumber) {

		PaymentPK pk = new PaymentPK(customerNumber, checkNumber);
		Optional<Payment> optional = repository.findById(pk);

		if (optional.isEmpty()) {
			return null;
		}

		Payment payment = optional.get();
		return mapper.paymentToDto(payment);
	}

	public DtoPayment savePayment(DtoPayment dtoPayment) {

		Payment payment = mapper.paymentFromDto(dtoPayment);

		if (!payment.hasValidIds()) {
			return null;
		}

		Optional<Payment> paymentInRepository = repository.findById(payment.getPaymentPK());

		if (paymentInRepository.isPresent()) {

			return null;
		}

		payment.setPaymentDate(currentDate());

		payment = repository.save(payment);

		return mapper.paymentToDto(payment);
	}

	public DtoPayment generatePayment(Order order) {

		Payment payment = new Payment();
		payment.setCustomer(order.getCustomer());
		payment.setAmount(order.getPrice());

		return mapper.paymentToDto(payment);
	}

	private Date currentDate() {

		return new Date(System.currentTimeMillis());
	}
}