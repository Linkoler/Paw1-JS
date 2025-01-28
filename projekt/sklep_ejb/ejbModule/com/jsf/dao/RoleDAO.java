package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Role;

@Stateless
public class RoleDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Role role) {
        em.persist(role);
    }

    public Role merge(Role role) {
        return em.merge(role);
    }

    public void remove(Role role) {
        em.remove(em.merge(role));
    }

    public Role find(Object id) {
        return em.find(Role.class, id);
    }

    public List<Role> getFullList() {
        List<Role> list = null;

        Query query = em.createQuery("SELECT r FROM Role r");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Role> getList(Map<String, Object> searchParams) {
        List<Role> list = null;

        String select = "select r ";
        String from = "from Role r ";
        String where = "";
              

        Query query = em.createQuery(select + from + where);


        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public Role findByName(String roleName) {
        try {
            return em.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                     .setParameter("roleName", roleName)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
