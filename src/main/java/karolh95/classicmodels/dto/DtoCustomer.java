package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoCustomer {
	private Long customerNumber;
	private String customerName;
	private String contactLastName;
	private String contactFirstName;
	private DtoAddress address;
	private String postalCode;
	private BigDecimal creditLimit;
	private Long salesRepEmployeeNumber;
}