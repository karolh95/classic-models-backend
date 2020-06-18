package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoOrderDetail {
	
	private Long orderNumber;
	private String productCode;
	private int quantityOrdered;
	private BigDecimal priceEach;
	private short orderLineNumber;
}