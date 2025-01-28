package com.jsfcourse.order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.ejb.EJB;
import com.jsf.dao.OrderDAO;
import com.jsf.entities.Order;
import com.jsf.entities.User;

@Named
@SessionScoped
public class OrderListBB implements Serializable {

    private Integer id; 
    private Integer idUs;
    
    @EJB
    private OrderDAO orderDAO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getidUs() {
        return idUs;
    }

    public void setidUs(Integer idUs) {
        this.idUs = idUs;
    }
    
   
    public List<Order> getFullList() {
        List<Order> orders = orderDAO.getList(new HashMap<>());
        
        
        orders.forEach(order -> {
            if (order.getConfirmed() == null) {
                order.setConfirmed(0); 
            }
        });

        return orders;
    }

    
    public List<Order> getList() {
        List<Order> list = null;
        Map<String, Object> searchParams = new HashMap<>();
        
        if (id == null || id <= 0) { 
            list = orderDAO.getFullList();
        } else {
            searchParams.put("id", id);
            list = orderDAO.getList(searchParams);
        }

        
        if (list != null) {
            list.forEach(order -> {
                if (order.getConfirmed() == null) {
                    order.setConfirmed(0); 
                }
            });
        }
        
        return list;
    }

    
    public List<Order> getUsList() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        RemoteClient<User> remoteClient = (RemoteClient<User>) session.getAttribute("remoteClient");
        if (remoteClient == null) {
            return null; 
        }

        Integer userId = remoteClient.getDetails().getIdUser();
        List<Order> list = null;
        Map<String, Object> searchParams = new HashMap<>();

        if (id == null || id <= 0) { 
            list = orderDAO.getUsFullList(userId);
        } else {
            searchParams.put("id", id);
            list = orderDAO.getUsList(searchParams, userId);
        }

        
        if (list != null) {
            list.forEach(order -> {
                if (order.getConfirmed() == null) {
                    order.setConfirmed(0); 
                }
            });
        }

        return list;
    }

    public String searchOrders() {
        return null; 
    }
    
    public String confirmOrder(Order order) {
        if (order.getConfirmed() == 0) {
            order.setConfirmed(1); 
            orderDAO.merge(order); 
        }
        return null; 
    }
}
