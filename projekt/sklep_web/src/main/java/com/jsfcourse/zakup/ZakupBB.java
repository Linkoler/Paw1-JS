package com.jsfcourse.zakup;

import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.ejb.EJB;

import com.jsfcourse.koszyk.KoszykBB;
import com.jsfcourse.koszyk.KoProBB;
import com.jsf.dao.OrderDAO;
import com.jsf.dao.OrderProductDAO;
import com.jsf.entities.Order;
import com.jsf.entities.OrderProduct;
import com.jsf.entities.User;


@Named
@SessionScoped
public class ZakupBB implements Serializable {

    private static final long serialVersionUID = 1L;
    private Order order = new Order();

    @EJB
    private OrderDAO orderDAO;

    @EJB
    private OrderProductDAO orderProductDAO;
    
    @Inject
	FacesContext context;

    @Inject
    private KoszykBB koszykBB;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void buy() {
    	if (koszykBB.getItems() != null && !koszykBB.getItems().isEmpty()) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);

			RemoteClient<User> remoteClient = (RemoteClient<User>) session.getAttribute("remoteClient");

			if (remoteClient != null) {

				User user = remoteClient.getDetails();

				order.setUser(user);
				order.setAmountToPay(koszykBB.getTotal());
				orderDAO.create(order);

				for (KoProBB item : koszykBB.getItems()) {
					OrderProduct orderProduct = new OrderProduct();
					orderProduct.setOrder(order);
					orderProduct.setQuantity(item.getQuantity());
					orderProduct.setProduct(item.getProduct());
					orderProductDAO.create(orderProduct);
				}
				koszykBB.removeAll();
			} else {
				
			}
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Koszyk jest pusty", null));
		}
	}
}
