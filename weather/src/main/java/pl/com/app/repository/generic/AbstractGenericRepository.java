package pl.com.app.repository.generic;

import pl.com.app.exception.MyException;
import pl.com.app.repository.generic.connection.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericRepository<T> implements GenericRepository<T> {

    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public T addOrUpdate(T item) {
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        T object = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            object = entityManager.merge(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("ADD OR UPDATE ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("ADD OR UPDATE EXCEPTION");
        }
        return object;
    }

    @Override
    public void addAll(List<T> items) {
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            for (T item : items) {
                entityManager.merge(item);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("ADD ALL ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("ADD ALL EXCEPTION");
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            T item = entityManager.find(entityClass, id);
            entityManager.remove(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("DELETE ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("DELETE EXCEPTION");
        }
    }

    @Override
    public void deleteAll() {
        List<T> items = null;
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            items = entityManager
                    .createQuery("select t from " + entityClass.getCanonicalName() + " t", entityClass)
                    .getResultList();
            for (T item : items) {
                entityManager.remove(item);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("DELETE ALL ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("DELETE ALL EXCEPTION");
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        Optional<T> op = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            op = Optional.of(entityManager.find(entityClass, id));
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ee) {
                    throw new MyException("FIND BY ID ROLLBACK EXCEPTION");
                }
            }
            throw new MyException("FIND BY ID EXCEPTION");
        }
        return op;
    }

    @Override
    public List<T> findAll() {
        List<T> items = null;
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try (DbConnection connection = new DbConnection()) {
            entityManager = connection.getEntityManager();
            tx = connection.getEntityTransaction();
            tx.begin();
            items = entityManager
                    .createQuery("select t from " + entityClass.getCanonicalName() + " t", entityClass)
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
