package karolh95.classicmodels.model;

import java.util.List;

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
@Entity(name = "employees")
public class Employee{

	// private static final long serialVersionUID = 2799740589882916086L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeNumber;

	@Column(length = 50, nullable = false)
	private String lastName;

	@Column(length = 50, nullable = false)
	private String firstName;

	@Column(length = 10, nullable = false)
	private String extension;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 50, nullable = false)
	private String jobTitle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reportsTo")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "officeCode", nullable = false)
	private Office office;

	// Mappings

	@OneToMany(mappedBy = "employee")
	private List<Employee> employees;

	@OneToMany(mappedBy = "employee")
	private List<Customer> customers;

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);
		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);
		return employee;
	}

	public Customer addcustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setEmployee(this);
		return customer;
	}

	public Customer removecustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setEmployee(null);
		return customer;
	}

}