package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_zam")
	private int idZam;

	private String address;

	@Column(name="amount_to_pay")
	private double amountToPay;

	private int confirmed;

	@Column(name="number_of_products")
	private int numberOfProducts;

	@Column(name="phone_number")
	private int phoneNumber;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to ProOr
	@OneToMany(mappedBy="order")
	private List<OrderProduct> proOrs;

	public Order() {
	}

	public int getIdZam() {
		return this.idZam;
	}

	public void setIdZam(int idZam) {
		this.idZam = idZam;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAmountToPay() {
		return this.amountToPay;
	}

	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

	public int getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getNumberOfProducts() {
		return this.numberOfProducts;
	}

	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderProduct> getProOrs() {
		return this.proOrs;
	}

	public void setProOrs(List<OrderProduct> proOrs) {
		this.proOrs = proOrs;
	}

	public OrderProduct addProOr(OrderProduct proOr) {
		getProOrs().add(proOr);
		proOr.setOrder(this);

		return proOr;
	}

	public OrderProduct removeProOr(OrderProduct proOr) {
		getProOrs().remove(proOr);
		proOr.setOrder(null);

		return proOr;
	}

}