package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type")
	private int idType;

	private String name;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="type")
	private List<Product> products;

	public Type() {
	}

	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setType(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setType(null);

		return product;
	}

}