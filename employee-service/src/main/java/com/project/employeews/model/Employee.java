package com.project.employeews.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "first_name", length = 32, nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "last_name", nullable = false, length = 32)
	private String lastName;

	/*
	 * unique in @Column is used only if you let your JPA provider create the
	 * database for you - it will create the unique constraint on the specified
	 * column. But if you already have the database, or you alter it once created,
	 * then unique doesn't have any effect.
	 */
	@NotEmpty
	@Column(unique = true, name = "email", length = 50, nullable = false)
	@Email
	private String email;

	public Employee() {
		super();
	}

	public Employee(Long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
