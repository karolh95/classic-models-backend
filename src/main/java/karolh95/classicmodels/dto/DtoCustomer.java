package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoCustomer {
	
	@NotNull
	private Long customerNumber;

	@NotEmpty
	@Size(max = 50)
	private String customerName;
	
	@NotEmpty
	@Size(max = 50)
	private String contactLastName;
	
	@NotEmpty
	@Size(max = 50)
	private String contactFirstName;
	
	@NotNull
	@Valid
	private DtoAddress address;
	
	@Size(max = 15)
	private String postalCode;

	@Digits(integer = 8, fraction = 2)
	private BigDecimal creditLimit;

	private Long salesRepEmployeeNumber;

}