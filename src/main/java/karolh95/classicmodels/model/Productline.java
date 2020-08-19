package karolh95.classicmodels.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "productlines")
public class Productline {

	@Id
	@Column(length = 50, nullable = false)
	private String productline;

	@Column(length = 4000)
	private String textDescription;

	@Column(columnDefinition = "mediumtext")
	private String htmlDescription;

	@Column(columnDefinition = "mediumblob")
	private byte[] image;

	@OneToMany(mappedBy = Product_.PRODUCTLINE)
	private List<Product> products;

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductline(this);
		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductline(null);
		return product;
	}

	public boolean hasValidIds() {

		if (productline == null) {
			return false;
		}

		return true;
	}

}
