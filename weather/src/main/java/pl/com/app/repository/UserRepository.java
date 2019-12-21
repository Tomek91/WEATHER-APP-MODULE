package pl.com.app.repository;


import pl.com.app.model.User;
import pl.com.app.repository.generic.GenericRepository;

import java.util.Optional;


public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByPassword(String password);
}
