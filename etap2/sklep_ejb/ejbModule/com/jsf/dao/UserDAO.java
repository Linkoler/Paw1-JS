package com.jsf.dao;

import java.util.List;
import java.util.Map;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.jsf.entities.User;

@Stateless
public class UserDAO {
    private static final String UNIT_NAME = "jsfcourse-simplePU";

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
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    public List<User> getList(Map<String, Object> searchParams) {
        String select = "SELECT u ";
        String from = "FROM User u ";
        String where = "";
        String orderBy = "ORDER BY u.lastName ASC, u.name";

        String lastName = (String) searchParams.get("lastName");
        if (lastName != null) {
            where = "WHERE LOWER(u.lastName) LIKE :lastName ";
        }

        Query query = em.createQuery(select + from + where + orderBy);

        if (lastName != null) {
            query.setParameter("lastName", "%" + lastName.toLowerCase() + "%");
        }

        return query.getResultList();
    }
}
