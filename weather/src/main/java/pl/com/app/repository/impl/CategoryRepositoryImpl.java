package pl.com.app.repository.impl;


import pl.com.app.exception.MyException;
import pl.com.app.model.Category;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.repository.generic.AbstractGenericRepository;
import pl.com.app.repository.generic.connection.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class CategoryRepositoryImpl extends AbstractGenericRepository<Category> implements CategoryRepository {

    @Override
    public Optional<Category> findByName(String name) {
        Optional<Category> op = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            if (name == null) {
                throw new NullPointerException("NAME IS NULL");
            }
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            op = entityManager
                    .createQuery("select c from Category c where c.name = :name", getEntityClass())
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .findFirst();
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("FIND BY NAME ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("FIND BY NAME EXCEPTION");
        }
        return op;
    }
}
