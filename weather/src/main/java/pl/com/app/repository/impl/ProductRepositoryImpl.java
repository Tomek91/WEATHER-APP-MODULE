package pl.com.app.repository.impl;


import pl.com.app.exception.MyException;
import pl.com.app.model.Product;
import pl.com.app.repository.ProductRepository;
import pl.com.app.repository.generic.AbstractGenericRepository;
import pl.com.app.repository.generic.connection.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProductRepositoryImpl extends AbstractGenericRepository<Product> implements ProductRepository {


    @Override
    public List<Product> findAllWithChildren() {
        List<Product> items = null;
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            items = entityManager
                    .createQuery("select distinct p from Product p join fetch p.category c join fetch c.weatherConditions w", getEntityClass())
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("FIND ALL ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("FIND ALL EXCEPTION");
        }
        return items;
    }
}
