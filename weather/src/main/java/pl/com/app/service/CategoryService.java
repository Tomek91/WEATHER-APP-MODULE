package pl.com.app.service;

import pl.com.app.dto.CategoryDto;
import pl.com.app.exception.MyException;
import pl.com.app.mappers.ModelMapper;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.repository.impl.CategoryRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private ModelMapper modelMapper = new ModelMapper();


    public void deleteAll() {
        try {
            categoryRepository.deleteAll();
        } catch (Exception e) {
            throw new MyException("CATEGORY SERVICE, DELETE ALL");
        }
    }

    public void addAll(List<CategoryDto> items) {
        try {
            if (items == null) {
                throw new NullPointerException("CATEGORIES IS NULL");
            }
            categoryRepository.addAll(
                    items
                            .stream()
                            .map(modelMapper::fromCategoryDtoToCategory)
                            .collect(Collectors.toList())
            );

        } catch (Exception e) {
            throw new MyException("CATEGORY SERVICE, ADD ALL");
        }
    }

}
