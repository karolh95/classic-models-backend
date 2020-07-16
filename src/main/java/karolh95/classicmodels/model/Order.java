package karolh95.classicmodels.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long orderNumber;

	@Column(nullable = false)
	private Date orderDate;

	@Column(nullable = false)
	private Date requiredDate;

	private Date shippedDate;

	@Column(length = 15, nullable = false)
	private String status;

	@Column(columnDefinition = "text")
	private String comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerNumber", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

	public OrderDetail addOrderDetails(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);
		return orderDetail;
	}

	public OrderDetail removeOrderDetails(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);
		return orderDetail;
	}

	public boolean hasValidIds() {

		if (customer == null) {
			return false;
		}

		return true;
	}

	public void sortOrderDetails() {
		getOrderDetails().sort(Comparator.comparing(OrderDetail::getOrderLineNumber));
	}

	public BigDecimal getPrice() {

		// @formatter:off
		BigDecimal ammount = getOrderDetails().stream()
			.map(OrderDetail::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		// @formatter:on

		return ammount;
	}
}