package com.jsfcourse.produkt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;

import com.jsf.dao.ProductDAO;
import com.jsf.entities.Product;

@Named
@RequestScoped
public class ProduktListBB {
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String name;

    @Inject
    ExternalContext extcontext;
    
    @Inject
    Flash flash;
    
    @EJB
    ProductDAO productDAO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getFullList(){
        return productDAO.getFullList();
    }

    public List<Product> getList(){
        List<Product> list = null;
        
        Map<String, Object> searchParams = new HashMap<String, Object>();
        
       if (name != null && name.length() > 0){
            searchParams.put("name", name);
        }
       
       list = productDAO.getList(searchParams);
       
        return list;
    }

}
