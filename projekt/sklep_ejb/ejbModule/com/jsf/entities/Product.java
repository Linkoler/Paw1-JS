package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */

@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produkty")
    private int idProdukty;

    private String description;

    private String name;

    @Column(name = "number_of")
    private int numberOf;

    private String photoName;

    private double price;

    @Column(name = "web_url")
    private String webUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> proOrs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "type_id_type", nullable = false)
    private ProductType type;


    public Product() {
    }

    public int getIdProdukty() {
        return idProdukty;
    }

    public void setIdProdukty(int idProdukty) {
        this.idProdukty = idProdukty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<OrderProduct> getProOrs() {
        return Collections.unmodifiableList(proOrs);
    }

    public void setProOrs(List<OrderProduct> proOrs) {
        this.proOrs = proOrs;
    }

    public OrderProduct addProOr(OrderProduct proOr) {
        proOrs.add(proOr);
        proOr.setProduct(this);
        return proOr;
    }

    public OrderProduct removeProOr(OrderProduct proOr) {
        proOrs.remove(proOr);
        proOr.setProduct(null);
        return proOr;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
