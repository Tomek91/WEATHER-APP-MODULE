package pl.com.app.repository;


import pl.com.app.model.Category;
import pl.com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface CategoryRepository extends GenericRepository<Category> {
    Optional<Category> findByName(String name);
}
