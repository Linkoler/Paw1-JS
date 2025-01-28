package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Order;

@Stateless
public class OrderDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Order order) {
        em.persist(order);
    }

    public Order merge(Order order) {
        return em.merge(order);
    }

    public void remove(Order order) {
        em.remove(em.merge(order));
    }

    public Order find(Object id) {
        return em.find(Order.class, id);
    }

    public List<Order> getFullList() {
        List<Order> list = null;

        Query query = em.createQuery("select o from Order o");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

	public List<Order> getList(Map<String, Object> searchParams) {
		List<Order> list = null;

		String select = "select o ";
		String from = "from Order o ";
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
	
	public List<Order> getUsFullList(Integer userId) {
	    List<Order> list = null;

	    String select = "select o ";
	    String from = "from Order o ";
	    String where = "where o.user.idUser = :userId ";
	    String orderby = "order by o.idZam desc";

	    Query query = em.createQuery(select + from + where + orderby);
	    query.setParameter("userId", userId);

	    try {
	        list = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	public List<Order> getUsList(Map<String, Object> searchParams, Integer userId) {
	    List<Order> list = null;

	    String select = "select o ";
	    String from = "from Order o ";
	    String where = "where o.user.idUser = :userId ";
	    String orderby = "order by o.idZam desc";

	    Integer id = (Integer) searchParams.get("id");
	    if (id != null) {
	        where += "and o.idZam = :id ";
	    }

	    Query query = em.createQuery(select + from + where + orderby);
	    query.setParameter("userId", userId);

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
