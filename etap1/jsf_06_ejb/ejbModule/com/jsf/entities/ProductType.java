package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */

@Entity
@Table(name = "type")
@NamedQuery(name = "ProductType.findAll", query = "SELECT t FROM ProductType t")
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private int idType;

    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public ProductType() {}

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
        this.products.add(product);
        product.setType(this);
        return product;
    }

    public Product removeProduct(Product product) {
        this.products.remove(product);
        product.setType(null);
        return product;
    }
}
