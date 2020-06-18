package karolh95.classicmodels.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import karolh95.classicmodels.dto.DtoAddress;
import karolh95.classicmodels.mapper.AddressMapper;
import karolh95.classicmodels.model.Address;
import karolh95.classicmodels.model.Customer;
import karolh95.classicmodels.repository.CustomerRepository;

@SpringBootTest
public class AddressMapperTests {
	
	@Autowired
	private AddressMapper mapper;

	@Autowired
	private CustomerRepository repository;

	@Test
	@DisplayName("Should map Address to Dto")
	public void mapAddressToDto(){

		Optional<Customer> optional = this.repository.findById(168L);

		assertTrue(optional.isPresent());

		Address address = optional.get().getAddress();
		DtoAddress dto = this.mapper.addressToDto(address) ;

		assertEquals(address.getPhone(), dto.getPhone(), "Phone should match");
		assertEquals(address.getAddressLine1(), dto.getAddressLine1(), "Address line 1 should match");
		assertEquals(address.getAddressLine2(), dto.getAddressLine2(), "Address line 2 should match");
		assertEquals(address.getCity(), dto.getCity(), "City should match");
		assertEquals(address.getState(), dto.getState(), "State should match");
		assertEquals(address.getCountry(), dto.getCountry(), "Country should match");
	}
}