package karolh95.classicmodels.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerNumber;

	@Column(length = 50, nullable = false)
	private String customerName;

	@Column(length = 50, nullable = false)
	private String contactLastName;

	@Column(length = 50, nullable = false)
	private String contactFirstName;

	@Embedded
	private Address address;

	@Column(length = 15)
	private String postalCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal creditLimit;

	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salesRepEmployeeNumber")
	private Employee employee;

	@Column(insertable = false, updatable = false)
	private Long salesRepEmployeeNumber;

	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Payment> payments;

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);
		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);
		return order;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setCustomer(this);
		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setCustomer(null);
		return payment;
	}

	public boolean hasValidIds() {

		if (customerNumber == null) {
			return false;
		}

		return true;
	}
}
