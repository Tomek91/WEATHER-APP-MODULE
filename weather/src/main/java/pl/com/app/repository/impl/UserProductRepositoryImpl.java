package pl.com.app.repository.impl;


import pl.com.app.exception.MyException;
import pl.com.app.model.Product;
import pl.com.app.model.UserProduct;
import pl.com.app.repository.ProductRepository;
import pl.com.app.repository.UserProductRepository;
import pl.com.app.repository.generic.AbstractGenericRepository;
import pl.com.app.repository.generic.connection.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserProductRepositoryImpl extends AbstractGenericRepository<UserProduct> implements UserProductRepository {


}
