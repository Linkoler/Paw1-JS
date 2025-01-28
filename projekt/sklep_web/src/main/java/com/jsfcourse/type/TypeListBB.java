package com.jsfcourse.type;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.ejb.EJB;
import com.jsf.dao.ProductDAO;
import com.jsf.dao.TypeDAO;
import com.jsf.entities.Product;
import com.jsf.entities.ProductType;

@Named
@SessionScoped
public class TypeListBB implements Serializable {
    private static final String PAGE_PRODUCT_LIST = "produkty.xhtml?faces-redirect=true&typeId=";

    private Integer idType;
    private Product selectedProduct;

    @EJB
    private TypeDAO typeDAO;

    @EJB
    private ProductDAO productDAO;

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<ProductType> getFullList() {
        return typeDAO.getFullList(); 
    }


    public String filterByType(Integer typeId) {
        this.idType = typeId;
        return PAGE_PRODUCT_LIST + typeId; 
    }
}
