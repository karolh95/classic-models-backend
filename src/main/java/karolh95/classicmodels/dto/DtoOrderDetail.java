package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DtoOrderDetail {

	@NotNull
	private Long orderNumber;

	@NotBlank
	private String productCode;

	@NotNull
	private int quantityOrdered;

	@NotNull
	@Digits(integer = 8, fraction = 2)
	private BigDecimal priceEach;

	@NotNull
	private short orderLineNumber;
}