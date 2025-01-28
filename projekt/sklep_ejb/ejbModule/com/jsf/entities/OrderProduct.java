package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


/**
 * The persistent class for the pro_or database table.
 * 
 */

@Entity
@Table(name = "pro_or")
@NamedQuery(name = "OrderProduct.findAll", query = "SELECT p FROM OrderProduct p")
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pro_or")
    private int idProOr;

    @Min(1) 
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orders_id_zam", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "products_id_produkty", nullable = false)
    private Product product;

    public OrderProduct() {}

    public int getIdProOr() {
        return idProOr;
    }

    public void setIdProOr(int idProOr) {
        this.idProOr = idProOr;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
