package pl.com.app.repository;


import pl.com.app.model.Product;
import pl.com.app.repository.generic.GenericRepository;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends GenericRepository<Product> {
    List<Product> findAllWithChildren();
}
