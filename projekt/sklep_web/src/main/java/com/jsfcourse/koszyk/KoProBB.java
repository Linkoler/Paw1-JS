package com.jsfcourse.koszyk;

import com.jsf.entities.Product;

public class KoProBB {

    private Product product;
    private int quantity;

    public KoProBB(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
