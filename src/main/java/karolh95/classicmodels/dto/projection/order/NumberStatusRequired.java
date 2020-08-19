package karolh95.classicmodels.dto.projection.order;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NumberStatusRequired {

	private Long orderNumber;
	private String status;
	private Date requiredDate;
}
