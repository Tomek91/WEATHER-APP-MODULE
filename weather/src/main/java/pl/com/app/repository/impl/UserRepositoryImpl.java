package pl.com.app.repository.impl;


import pl.com.app.exception.MyException;
import pl.com.app.model.User;
import pl.com.app.repository.UserRepository;
import pl.com.app.repository.generic.AbstractGenericRepository;
import pl.com.app.repository.generic.connection.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class UserRepositoryImpl extends AbstractGenericRepository<User> implements UserRepository {


    @Override
    public Optional<User> findByPassword(String password) {
        Optional<User> op = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            op = entityManager.createQuery("select u from User u where u.password = :password", getEntityClass())
                    .setParameter("password", password)
                    .getResultList()
                    .stream()
                    .findFirst();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("FIND BY PASSWORD ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("FIND BY PASSWORD EXCEPTION");
        }
        return op;
    }
}
