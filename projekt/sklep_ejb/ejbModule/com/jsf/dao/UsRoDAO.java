package com.jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Role;
import com.jsf.entities.UsRo;

@Stateless
public class UsRoDAO {
    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(UsRo usro) {
        em.persist(usro);
    }

    public UsRo merge(UsRo usro) {
        return em.merge(usro);
    }

    public void remove(UsRo usro) {
        em.remove(em.merge(usro));
    }

    public UsRo find(Object id) {
        return em.find(UsRo.class, id);
    }

    public List<UsRo> getFullList() {
    	List<UsRo> list = null;
        Query query = em.createQuery("SELECT us FROM UsRo us");
        
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public List<UsRo> getList(Map<String, Object> searchParams) {
        String select = "SELECT us ";
        String from = "FROM UsRo us ";
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
    
    public UsRo getUserFromDatabase(String mail, String password) {
        try {
            Query query = em.createQuery(
                "SELECT us FROM UsRo us " +
                "LEFT JOIN FETCH us.usRos ur " +
                "LEFT JOIN FETCH ur.role r " +
                "WHERE u.mail = :mail AND u.password = :password"
            );
            query.setParameter("mail", mail);
            query.setParameter("password", password); 
            return (UsRo) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }

    
    public List<String> getUserRolesFromDatabase(UsRo usro) {
        try {
            
            Query query = em.createQuery(
                "SELECT r.roleName FROM Role r " +
                "JOIN UsRo ur ON r.idRole = ur.role.idRole " +
                "WHERE ur.user.idUser = :userId"
            );
            query.setParameter("usroId", usro.getIdUsRo());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }

}
