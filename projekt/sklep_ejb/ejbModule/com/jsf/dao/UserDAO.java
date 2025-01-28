package com.jsf.dao;

import java.util.ArrayList;
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
    
    public User getUserFromDatabase(String login, String pass) {
    	String select = "select u ";
		String from = "from User u ";
		String where = "where u.mail = :login AND u.password = :pass";

		try {
			Query query = em.createQuery(select + from + where);

			query.setParameter("login", login);
			query.setParameter("pass", pass);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
    }

    
    public List<String> getUserRolesFromDatabase(User user) {

		Query query = em.createQuery("SELECT u.role.roleName FROM UsRo u WHERE u.user.mail = :mail");

		query.setParameter("mail", user.getMail());

		List<String> result = query.getResultList();

		ArrayList<String> roles = new ArrayList<String>();

		if (result != null && !result.isEmpty()) {
			if (result.contains("user")) {
				roles.add("user");
			} else if (result.contains("admin")) {
				roles.add("admin");
			} else if (result.contains("empl")) {
				roles.add("empl");
			}
		}
		return roles;
	}

}
