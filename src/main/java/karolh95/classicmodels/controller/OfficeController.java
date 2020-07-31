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
import karolh95.classicmodels.controller.mapping.Office;
import karolh95.classicmodels.dto.DtoOffice;
import karolh95.classicmodels.service.OfficeService;

@RestController
@RequestMapping(Mappings.OFFICE)
public class OfficeController {

	@Autowired
	private OfficeService service;

	@GetMapping(Office.ALL)
	public List<DtoOffice> getAllOffices() {

		return service.getAllOffices();
	}

	@GetMapping(Office.GET)
	public ResponseEntity<DtoOffice> getOffice(@PathVariable String id) {

		DtoOffice response = service.getOffice(id);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(Office.SAVE)
	public ResponseEntity<DtoOffice> save(@Valid @RequestBody DtoOffice office,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(office);
		}

		DtoOffice response = service.saveOffice(office);

		if (response == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
}
