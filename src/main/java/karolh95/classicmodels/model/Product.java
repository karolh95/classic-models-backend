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

import lombok.Data;

@Data
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
	private int quantityInStock;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal buyPrice;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal MSRP;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Productline_.PRODUCTLINE, nullable = false)
	private Productline productline;

	@Column(name = Product_.PRODUCTLINE, insertable = false, updatable = false)
	private String productline_id;

	@OneToMany(mappedBy = OrderDetail_.PRODUCT, cascade = CascadeType.ALL)
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

	public boolean hasValidIds() {

		if (productCode == null) {
			return false;
		}

		if (productline == null) {
			return false;
		}

		return true;
	}
}
