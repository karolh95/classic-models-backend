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

import lombok.Data;

@Data
@Entity(name = "employees")
public class Employee {

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
	@JoinColumn(name = Employee_.REPORTS_TO)
	private Employee employee;

	@Column(insertable = false, updatable = false)
	private Long reportsTo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Office_.OFFICE_CODE, nullable = false)
	private Office office;

	@Column(insertable = false, updatable = false)
	private String officeCode;

	@OneToMany(mappedBy = Employee_.EMPLOYEE)
	private List<Employee> employees;

	@OneToMany(mappedBy = Customer_.EMPLOYEE)
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

	public boolean hasValidIds() {

		if (employeeNumber == null || office == null) {
			return false;
		}

		return true;
	}
}
