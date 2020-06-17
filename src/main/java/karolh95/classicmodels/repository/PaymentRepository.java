package karolh95.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karolh95.classicmodels.model.Payment;
import karolh95.classicmodels.model.PaymentPK;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentPK> {

}