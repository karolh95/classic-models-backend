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
import karolh95.classicmodels.controller.mapping.Order;
import karolh95.classicmodels.dto.DtoFullOrder;
import karolh95.classicmodels.dto.DtoPayment;
import karolh95.classicmodels.dto.DtoSimpleOrder;
import karolh95.classicmodels.service.OrderService;

@RestController
@RequestMapping(Mappings.ORDER)
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping(Order.ALL)
	public List<DtoSimpleOrder> getAllOrders() {

		return service.getAllOrders();
	}

	@GetMapping(Order.GET)
	public ResponseEntity<DtoFullOrder> getOrder(@PathVariable Long id) {

		DtoFullOrder response = service.getOrder(id);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(Order.SAVE)
	public ResponseEntity<DtoFullOrder> save(@Valid @RequestBody DtoFullOrder order,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(order);
		}

		DtoFullOrder response = service.saveOrder(order);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(Order.PAYMENT_GENERATE)
	public ResponseEntity<DtoPayment> generatePayment(@Valid @RequestBody DtoFullOrder order) {

		DtoPayment response = service.generatePayment(order);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(Order.PAYMENT_MAKE)
	public ResponseEntity<DtoPayment> makePayment(@Valid @RequestBody DtoPayment payment) {

		DtoPayment response = service.makePayment(payment);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);

	}
}
