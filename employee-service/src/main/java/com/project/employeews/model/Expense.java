package com.project.employeews.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "expenses")
public class Expense {

//	@ManyToOne
//	@JoinColumn(name = "troop_fk", insertable = false, updatable = false)
//	public Troop getTroop() {
//
//	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Column(unique = true, name = "expense_name")
	private String expenseName;

	@NotEmpty
	@Column(name = "vendor", length = 32, nullable = false)
	private String vendor;

	@Min(1)
	private double amount;

	@NotEmpty
	@Column(name = "description", length = 32, nullable = false)
	private String description;

	public Expense() {
		super();
	}

	public Expense(@NotEmpty String expenseName, @NotEmpty String vendor, Double amount, @NotEmpty String description) {
		super();
		this.expenseName = expenseName;
		this.vendor = vendor;
		this.amount = amount;
		this.description = description;
	}

	public Long getEid() {
		return id;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
