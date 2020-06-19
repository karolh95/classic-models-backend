package karolh95.classicmodels.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoOrder {
	
	private Long orderNumber;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private String status;
	private String comments;
	private Long customerNumber;

	public boolean isValid() {

		if (orderNumber == null) {
			return false;
		}

		if (customerNumber == null) {
			return false;
		}

		if (orderDate == null) {
			return false;
		}

		if (requiredDate == null) {
			return false;
		}

		if (status == null) {
			return false;
		}

		return true;
	}
}