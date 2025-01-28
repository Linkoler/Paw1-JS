package com.jsfcourse.orderproduct;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.ejb.EJB;
import com.jsf.dao.OrderProductDAO;
import com.jsf.entities.OrderProduct;
import com.jsf.entities.Order;
import com.jsf.entities.Product;

@Named
@SessionScoped
public class OrderProductListBB implements Serializable {

    private Integer id; 

    @EJB
    private OrderProductDAO orderproductDAO;

    // Gettery i settery dla nowych pól

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
    public List<OrderProduct> getFullList() {
        return orderproductDAO.getList(new HashMap<>());
    }

    
    public List<OrderProduct> getList() {
        List<OrderProduct> list = null;
        Map<String, Object> searchParams = new HashMap<>();
        
        if (id == null || id <= 0) { 
            list = orderproductDAO.getFullList();
        } else {
            searchParams.put("id", id);
            list = orderproductDAO.getList(searchParams);
        }
        
        return list;
    }

    public String searchOrders() {
        
        return null; 
    }
    
    
        
}
