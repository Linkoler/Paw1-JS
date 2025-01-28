package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.enterprise.lang.model.types.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;


import com.jsf.entities.Product;
import com.jsf.entities.ProductType;

@Stateless
public class TypeDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";
    private static final Logger logger = Logger.getLogger(TypeDAO.class.getName());


    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    private ProductDAO productdao;
    public void create(ProductType type) {
        em.persist(type);
    }

    public ProductType merge(ProductType type) {
        return em.merge(type);
    }

    public void remove(ProductType type) {
        em.remove(em.merge(type));
    }

    public ProductType find(Object id) {
        return em.find(ProductType.class, id);
    }

    public List<ProductType> getFullList() {
        List<ProductType> list = null;

        
        Query query = em.createQuery("SELECT t FROM ProductType t");

        try {
            list = query.getResultList();
            System.out.println("Typy produktów znalezione w bazie:");
            for (ProductType type : list) {
                System.out.println("ID: " + type.getIdType() + ", Nazwa: " + type.getName());
            }

        } catch (Exception e) {
            logger.severe("Error fetching full product list: " + e.getMessage());
        }

        return list;
    }

}

