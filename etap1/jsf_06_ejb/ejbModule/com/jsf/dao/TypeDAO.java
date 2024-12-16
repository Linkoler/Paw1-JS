package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.User;

@Stateless
public class TypeDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public User merge(User user) {
        return em.merge(user);
    }

    public void remove(User user) {
        em.remove(em.merge(user));
    }

    public User find(Object id) {
        return em.find(User.class, id);
    }

    public List<User> getFullList() {
        List<User> list = null;

        Query query = em.createQuery("select u from User u");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<User> getList(Map<String, Object> searchParams) {
        List<User> list = null;

        String select = "select u ";
        String from = "from User u ";
        String where = "";
        String orderby = "order by u.lastName asc, u.name";

        String lastName = (String) searchParams.get("lastName");
        if (lastName != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.lastName like :lastName ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (lastName != null) {
            query.setParameter("lastName", lastName + "%");
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
