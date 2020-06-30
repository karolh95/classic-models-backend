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

import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("all")
	public List<DtoSimpleOrder> getAllOrders() {

		return service.getAllOrders();
	}

	@GetMapping("detail/{orderNumber}")
	public ResponseEntity<DtoFullOrder> getOrder(@PathVariable Long orderNumber) {

		DtoFullOrder response = service.getOrder(orderNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("save")
	public ResponseEntity<DtoFullOrder> save(@Valid @RequestBody DtoFullOrder order, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(order);
		}

		DtoFullOrder response = service.saveOrder(order);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("generatePayment")
	public ResponseEntity<DtoPayment> generatePayment(@Valid @RequestBody DtoFullOrder order) {

		DtoPayment response = service.generatePayment(order);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}