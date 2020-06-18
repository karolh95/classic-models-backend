package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoOrder;
import karolh95.classicmodels.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("all")
	public List<DtoOrder> getAllOrders() {

		return this.service.getAllOrders();
	}

	@GetMapping("detail/{orderNumber}")
	public ResponseEntity<DtoOrder> getOrder(@PathVariable Long orderNumber) {

		DtoOrder response = this.service.getOrder(orderNumber);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}