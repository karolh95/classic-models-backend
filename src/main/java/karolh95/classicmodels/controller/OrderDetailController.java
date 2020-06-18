package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoOrderDetail;
import karolh95.classicmodels.service.OrderDetailService;

@RestController
@RequestMapping("orderdetails")
public class OrderDetailController {

	@Autowired
	private OrderDetailService service;

	@GetMapping("all")
	public List<DtoOrderDetail> getAllOrderDetails() {

		return service.getAllOrderDetails();
	}

	@GetMapping("detail/{orderNumber}")
	public List<DtoOrderDetail> getOrderDetails(@PathVariable Long orderNumber) {

		return service.getOrderDetails(orderNumber);
	}

	@GetMapping("detail/{orderNumber}/{productCode}")
	public ResponseEntity<DtoOrderDetail> getOrderDetail(@PathVariable Long orderNumber,
			@PathVariable String productCode) {

		DtoOrderDetail response = service.getOrderDetail(orderNumber, productCode);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

}