package karolh95.classicmodels.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "payments")
public class Payment {

	@EmbeddedId
	private PaymentPK paymentPK;

	@Column(insertable = false, updatable = false)
	private String checkNumber;

	@Column(insertable = false, updatable = false)
	private Long customerNumber;

	private Date paymentDate;

	@Column(precision = 10, scale = 2)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(PaymentPK_.CUSTOMER_NUMBER)
	@JoinColumn(name = Customer_.CUSTOMER_NUMBER)
	private Customer customer;

	public boolean hasValidIds() {

		if (paymentPK == null) {
			return false;
		}

		if (customer == null) {
			return false;
		}

		return true;
	}
}
