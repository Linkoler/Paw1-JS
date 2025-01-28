package com.jsf.dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Product;

@Stateless
public class ProductDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";
    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Product product) {
        em.persist(product);
    }

    public Product merge(Product product) {
        return em.merge(product);
    }

    public void remove(Product product) {
    	Product managedProduct = em.find(Product.class, product.getIdProdukty());
        if (managedProduct != null) {
            em.remove(managedProduct);
        } else {
        }
    }

    public Product find(Object id) {
        return em.find(Product.class, id);
    }

    public List<Product> getList(Map<String, Object> searchParams) {
        String select = "select p ";
        String from = "from Product p ";
        String where = "";
        String orderby = "order by p.name asc";

        String name = (String) searchParams.get("name");
        if (name != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "lower(p.name) like :name ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (name != null) {
            query.setParameter("name", "%" + name.toLowerCase() + "%");
        }

        return query.getResultList();
    }


    public List<Product> orderUp(Map<String, Object> searchParams) {
        return getSortedList(searchParams, "asc");
    }

    public List<Product> orderDown(Map<String, Object> searchParams) {
        return getSortedList(searchParams, "desc");
    }

    private List<Product> getSortedList(Map<String, Object> searchParams, String order) {
        String select = "select p ";
        String from = "from Product p ";
        String where = "";
        String orderby = "order by p.name " + order;

        String name = (String) searchParams.get("name");
        if (name != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "lower(p.name) like :name ";
        }

        Query query = em.createQuery(select + from + where + orderby);
        if (name != null) {
            query.setParameter("name", "%" + name.toLowerCase() + "%");
        }

        return query.getResultList();
    }

    
    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }

    public List<Product> getProductsByType(Map<String, Object> typeId) {
    	Query query = em.createQuery("SELECT p FROM Product p WHERE p.type = :typeId");

        query.setParameter("typeId", typeId.get("typeId")); 

        return query.getResultList();
    }
    
    public List<Product> orderByPrice(Map<String, Object> searchParams, String order) {
        String select = "select p ";
        String from = "from Product p ";
        String where = "";
        String orderby = "order by p.price " + order;

        String name = (String) searchParams.get("name");
        if (name != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "lower(p.name) like :name ";
        }

        Query query = em.createQuery(select + from + where + orderby);
        if (name != null) {
            query.setParameter("name", "%" + name.toLowerCase() + "%");
        }

        return query.getResultList();
    }

}

