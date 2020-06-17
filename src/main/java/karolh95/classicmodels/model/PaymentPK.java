package karolh95.classicmodels.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class PaymentPK implements Serializable {

	private static final long serialVersionUID = -2019193431325532163L;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long customerNumber;

	@Column(length = 50, insertable = false, updatable = false, nullable = false)
	private String checkNumber;

}