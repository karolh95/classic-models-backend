package karolh95.classicmodels.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "offices")
public class Office {

	@Id
	@Column(length = 10, nullable = false)
	private String officeCode;

	@Embedded
	private Address address;

	@Column(length = 15, nullable = false)
	private String postalCode;

	@Column(length = 10, nullable = false)
	private String territory;

	@OneToMany(mappedBy = Employee_.OFFICE)
	private List<Employee> employees;

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setOffice(this);
		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setOffice(null);
		return employee;
	}

	public boolean hasValidIds() {

		if (officeCode == null) {
			return false;
		}

		return true;
	}
}
