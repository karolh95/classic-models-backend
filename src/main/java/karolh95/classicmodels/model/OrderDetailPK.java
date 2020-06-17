package karolh95.classicmodels.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class OrderDetailPK implements Serializable {

	private static final long serialVersionUID = 9156646711477906187L;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long orderNumber;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private String productCode;
}