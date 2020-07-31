package karolh95.classicmodels.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.classicmodels.controller.mapping.Mappings;
import karolh95.classicmodels.controller.mapping.Payment;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.service.PaymentService;

@RestController
@RequestMapping(Mappings.PAYMENT)
public class PaymentController {

	@Autowired
	private PaymentService service;

	@GetMapping(Payment.ALL)
	public List<DtoPayment> getAllPayments() {

		return service.getAllPayments();
	}

	@GetMapping(Payment.GET_BY_CUSTOMER)
	public List<DtoPayment> getPayments(@PathVariable Long customerNumber) {
		return service.getPayments(customerNumber);
	}

	@GetMapping(Payment.GET_PAYMENT)
	public ResponseEntity<DtoPayment> getPayment(@PathVariable Long customerNumber,
			@PathVariable String checkNumber) {

		DtoPayment response = service.getPayment(customerNumber, checkNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(Payment.SAVE)
	public ResponseEntity<DtoPayment> save(@Valid @RequestBody DtoPayment payment,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(payment);
		}

		DtoPayment response = service.savePayment(payment);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}
