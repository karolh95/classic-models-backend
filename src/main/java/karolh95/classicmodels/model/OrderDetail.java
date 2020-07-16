package karolh95.classicmodels.model;

import java.math.BigDecimal;

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
@Entity(name = "orderdetails")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailPK orderDetailPK;

	private int quantityOrdered;

	@Column(precision = 10, scale = 2)
	private BigDecimal priceEach;

	@Column(nullable = false)
	private short orderLineNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("customerNumber")
	@JoinColumn(name = "orderNumber")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productCode")
	@JoinColumn(name = "productCode")
	private Product product;

	public boolean hasValidIds() {

		if (orderDetailPK == null) {
			return false;
		}

		if (order == null || product == null) {
			return false;
		}

		return true;
	}

	public BigDecimal getPrice() {

		BigDecimal quantity = new BigDecimal(quantityOrdered);

		return priceEach.multiply(quantity);
	}
}