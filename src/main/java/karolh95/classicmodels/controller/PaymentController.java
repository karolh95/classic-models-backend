package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.service.PaymentService;

@RestController
@RequestMapping("payments")
public class PaymentController {

	@Autowired
	private PaymentService service;

	@GetMapping("all")
	public List<DtoPayment> getAllPayments() {

		return this.service.getAllPayments();
	}

	@GetMapping("detail/{customerNumber}")
	public List<DtoPayment> getPayments(@PathVariable Long customerNumber) {
		return this.service.getPayments(customerNumber);
	}

	@GetMapping("detail/{customerNumber}/{checkNumber}")
	public ResponseEntity<DtoPayment> getPayment(@PathVariable Long customerNumber, @PathVariable String checkNumber) {

		DtoPayment response = this.service.getPayment(customerNumber, checkNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}