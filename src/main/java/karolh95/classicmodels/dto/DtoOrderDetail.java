package karolh95.classicmodels.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.Data;

@Data
public class DtoOrderDetail {

	private Long orderNumber;
	private String productCode;
	private int quantityOrdered;
	private BigDecimal priceEach;
	private short orderLineNumber;
}