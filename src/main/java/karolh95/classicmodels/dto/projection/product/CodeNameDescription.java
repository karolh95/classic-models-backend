package karolh95.classicmodels.dto.projection.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeNameDescription {

	private String productCode;
	private String productName;
	private String textDescription;
}
