package karolh95.classicmodels.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@JoinColumn(name = "orderNumber", insertable = false, updatable = false, nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productCode", insertable = false, updatable = false, nullable = false)
	private Product product;
}