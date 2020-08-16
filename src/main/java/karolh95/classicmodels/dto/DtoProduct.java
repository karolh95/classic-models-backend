package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoProduct {

	@NotBlank
	@Size(max = 15)
	private String productCode;

	@NotBlank
	@Size(max = 70)
	private String productName;

	@NotBlank
	@Size(max = 10)
	private String productScale;

	@NotBlank
	@Size(max = 50)
	private String productVendor;

	@NotBlank
	private String productDescription;

	@NotNull
	private int quantityInStock;

	@NotNull
	@Digits(integer = 8, fraction = 2)
	private BigDecimal MSRP;

	@NotNull
	@Digits(integer = 8, fraction = 2)
	private BigDecimal buyPrice;

	@NotBlank
	private String productline;
}
