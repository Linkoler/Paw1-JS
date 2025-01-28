package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.OrderProduct;

@Stateless
public class OrderProductDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(OrderProduct orderproduct) {
        em.persist(orderproduct);
    }

    public OrderProduct merge(OrderProduct orderproduct) {
        return em.merge(orderproduct);
    }

    public void remove(OrderProduct orderproduct) {
        em.remove(em.merge(orderproduct));
    }

    public OrderProduct find(Object id) {
        return em.find(OrderProduct.class, id);
    }

    public List<OrderProduct> getFullList() {
        List<OrderProduct> list = null;

        Query query = em.createQuery("select o from OrderProduct o");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

	public List<OrderProduct> getList(Map<String, Object> searchParams) {
		List<OrderProduct> list = null;

		String select = "select o ";
		String from = "from OrderProduct o ";
		String where = "";
		String orderby = "order by o.idZam desc";

		Integer id = (Integer) searchParams.get("id");
		if (id != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "o.idZam = :id ";
		}

		Query query = em.createQuery(select + from + where + orderby);

		if (id != null) {
		    query.setParameter("id", id);
		}
		try {
		    list = query.getResultList();
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return list;
	}
	
	
	
}
