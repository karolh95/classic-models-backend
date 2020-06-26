package karolh95.classicmodels.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoSimpleOrder {

	private Long orderNumber;

	@NotNull
	private Date orderDate;

	@NotNull
	private Date requiredDate;

	@NotNull
	private Date shippedDate;

	@NotBlank
	@Size(max = 15)
	private String status;

	private String comments;

	@NotNull
	private Long customerNumber;

}