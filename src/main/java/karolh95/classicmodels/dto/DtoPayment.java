package karolh95.classicmodels.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoPayment {
	
	private Long customerNumber;
	private String checkNumber;
	private Date paymentDate;
	private BigDecimal amount;
}