package karolh95.classicmodels.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {

	@Column(length = 50, nullable = false)
	private String phone;
	@Column(length = 50, nullable = false)
	private String addressLine1;
	@Column(length = 50)
	private String addressLine2;
	@Column(length = 50, nullable = false)
	private String city;
	@Column(length = 50)
	private String state;
	@Column(length = 50, nullable = false)
	private String country;
}