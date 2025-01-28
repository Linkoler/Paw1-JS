package com.jsfcourse.produkt;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.ejb.EJB;
import com.jsf.dao.ProductDAO;
import com.jsf.entities.Product;

@Named
@SessionScoped
public class ProduktListBB implements Serializable {
    private static final String PAGE_PRODUCT_DETAIL = "productDetail.xhtml?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String name; 
    private Integer typeId; 
    private String sortOption = "asc"; 
    private Product selectedProduct;

    @EJB
    private ProductDAO productDAO;

    // Gettery i settery dla nowych pól
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getSortOption() {
        return sortOption;
    }

    public void setSortOption(String sortOption) {
        this.sortOption = sortOption;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getList() {
        List<Product> list = null;

        
        Map<String, Object> searchParams = new HashMap<>();
        if (name != null && !name.isEmpty()) {
            searchParams.put("name", name);
        }

        
        switch (sortOption) {
            case "priceAsc":
                list = productDAO.orderByPrice(searchParams, "asc");
                break;
            case "priceDesc":
                list = productDAO.orderByPrice(searchParams, "desc");
                break;
            case "asc":
                list = productDAO.orderUp(searchParams);
                break;
            case "desc":
                list = productDAO.orderDown(searchParams);
                break;
            default:
                list = productDAO.getList(searchParams);
        }

        return list;
    }


    public String sortList() {
        return PAGE_STAY_AT_THE_SAME;
    }

    public String viewProduct(Integer productId) {
        selectedProduct = productDAO.find(productId);

        if (selectedProduct == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_PRODUCT_DETAIL;
    }
}
