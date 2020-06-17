package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DTOProduct {
	
	private String productCode;
	private String productName;
	private String productScale;
	private String productVendor;
	private String productDescription;
	private short quantityInStock;
	private BigDecimal MSRP;
	private String productline;
}