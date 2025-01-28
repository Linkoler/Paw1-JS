package com.jsfcourse.koszyk;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import com.jsf.entities.Product;

@Named
@SessionScoped
public class KoszykBB implements Serializable {

    private List<KoProBB> items = new ArrayList<>();

    public List<KoProBB> getItems() {
        return items;
    }

    public void addToKoszyk(Product product) {
        for (KoProBB item : items) {
            if (item.getProduct().getIdProdukty() == product.getIdProdukty()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new KoProBB(product, 1));
    }

    public void removeFromKoszyk(Product product) {
        items.removeIf(item -> item.getProduct().getIdProdukty() == product.getIdProdukty());
    }

    public void removeAll() {
        items.clear();
    }

    public double getTotal() {
        //return items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    	double total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        
        
        BigDecimal roundedTotal = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
        return roundedTotal.doubleValue();
    }
}
