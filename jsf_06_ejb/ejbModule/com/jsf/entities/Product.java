package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_produkty")
	private int idProdukty;

	private String description;

	private String name;

	@Column(name="number_of")
	private int numberOf;

	private String photoName;

	private double price;

	@Column(name="web_url")
	private String webUrl;

	//bi-directional many-to-one association to ProOr
	@OneToMany(mappedBy="product")
	private List<ProOr> proOrs;

	//bi-directional many-to-one association to Type
	@ManyToOne
	private Type type;

	public Product() {
	}

	public int getIdProdukty() {
		return this.idProdukty;
	}

	public void setIdProdukty(int idProdukty) {
		this.idProdukty = idProdukty;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOf() {
		return this.numberOf;
	}

	public void setNumberOf(int numberOf) {
		this.numberOf = numberOf;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public List<ProOr> getProOrs() {
		return this.proOrs;
	}

	public void setProOrs(List<ProOr> proOrs) {
		this.proOrs = proOrs;
	}

	public ProOr addProOr(ProOr proOr) {
		getProOrs().add(proOr);
		proOr.setProduct(this);

		return proOr;
	}

	public ProOr removeProOr(ProOr proOr) {
		getProOrs().remove(proOr);
		proOr.setProduct(null);

		return proOr;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}