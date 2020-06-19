package karolh95.classicmodels.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Entity(name = "products")
public class Product {

	@Id
	@Column(length = 15, nullable = false)
	private String productCode;

	@Column(length = 70, nullable = false)
	private String productName;

	@Column(length = 10, nullable = false)
	private String productScale;

	@Column(length = 50, nullable = false)
	private String productVendor;

	@Column(columnDefinition = "text NOT NULL")
	private String productDescription;

	@Column(nullable = false)
	private short quantityInStock;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal buyPrice;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal MSRP;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productline", nullable = false)
	private Productline productline;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	List<OrderDetail> orderDetails;

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setProduct(this);
		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setProduct(null);
		return orderDetail;
	}
}