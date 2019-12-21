package pl.com.app.repository;


import pl.com.app.model.UserCategory;
import pl.com.app.repository.generic.GenericRepository;

import java.util.Optional;


public interface UserCategoryRepository extends GenericRepository<UserCategory> {
    Optional<UserCategory> findByName(String name);
}
