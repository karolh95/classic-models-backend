package karolh95.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentPK> {

	List<Payment> findByPaymentPKCustomerNumber(Long customerNumber);
}