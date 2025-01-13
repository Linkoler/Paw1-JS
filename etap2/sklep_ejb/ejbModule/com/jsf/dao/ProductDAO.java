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
            logger.warning("Product not found for removal: " + product.getIdProdukty());
        }
    }

    public Product find(Object id) {
        return em.find(Product.class, id);
    }

    public List<Product> getFullList() {
        List<Product> list = null;


        Query query = em.createQuery("select p from Product p");

        try {
            list = query.getResultList();

        } catch (Exception e) {
            logger.severe("Error fetching full product list: " + e.getMessage());
        }

        return list;
    }


    public List<Product> getList(Map<String, Object> searchParams) {
        List<Product> list = null;

        String select = "select p ";
        String from = "from Product p ";
        String where = "";
        String orderby = "order by p.name asc";

        String Name = (String) searchParams.get("name");
        if (Name != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "lower(p.name) like :name ";
        }


        Query query = em.createQuery(select + from + where + orderby);

        if (Name != null) {
            query.setParameter("name","%" + Name.toLowerCase() + "%");
        }

        try {
            list = query.getResultList();

        } catch (Exception e) {
            logger.severe("Error fetching filtered product list: " + e.getMessage());
        }

        return list;
    }

}

