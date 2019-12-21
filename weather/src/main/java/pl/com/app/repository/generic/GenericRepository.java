package pl.com.app.repository.generic;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    T addOrUpdate(T item);
    void addAll(List<T> items);
    void delete(Long id);
    void deleteAll();
    Optional<T> findById(Long id);
    List<T> findAll();
}
