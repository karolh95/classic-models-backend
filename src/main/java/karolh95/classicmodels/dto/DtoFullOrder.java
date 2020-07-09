package karolh95.classicmodels.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoFullOrder extends DtoSimpleOrder {

	@NotEmpty
	private List<@Valid DtoOrderDetail> orderDetails;
}