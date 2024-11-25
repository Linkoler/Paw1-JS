package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the pro_or database table.
 * 
 */
@Entity
@Table(name="pro_or")
@NamedQuery(name="ProOr.findAll", query="SELECT p FROM ProOr p")
public class ProOr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pro_or")
	private int idProOr;

	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="orders_id_zam")
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="products_id_produkty")
	private Product product;

	public ProOr() {
	}

	public int getIdProOr() {
		return this.idProOr;
	}

	public void setIdProOr(int idProOr) {
		this.idProOr = idProOr;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}