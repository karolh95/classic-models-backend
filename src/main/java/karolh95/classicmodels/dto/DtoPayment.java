package karolh95.classicmodels.dto;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoPayment {

	@NotNull
	private Long customerNumber;

	@NotNull
	@Size(max = 50)
	private String checkNumber;

	private Date paymentDate;

	@Digits(integer = 8, fraction = 2)
	private BigDecimal amount;
}