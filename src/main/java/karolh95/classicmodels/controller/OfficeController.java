package karolh95.classicmodels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.service.OfficeService;

@RestController
@RequestMapping("offices")
public class OfficeController {

	@Autowired
	private OfficeService service;

	@GetMapping("all")
	public List<DtoOffice> getAllOffices() {

		return service.getAllOffices();
	}

	@GetMapping("detail/{officeCode}")
	public ResponseEntity<DtoOffice> getOffice(@PathVariable String officeCode) {

		DtoOffice response = service.getOffice(officeCode);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}