package karolh95.classicmodels.dto.projection.order;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithProductPrice {

	private Long orderNumber;
	private Date orderDate;

	private short orderLineNumber;
	private BigDecimal priceEach;
	private int quantityOrdered;

	private String productName;
}
