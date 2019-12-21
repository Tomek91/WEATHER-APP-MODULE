package pl.com.app.service;

import pl.com.app.dto.*;
import pl.com.app.exception.MyException;
import pl.com.app.json.*;
import pl.com.app.mappers.ModelMapper;
import pl.com.app.model.UserProduct;

import java.util.List;

public class DataLoaderService {
    private ModelMapper modelMapper;

    public List<ProductDto> loadProducts(String fileName) {
        if (fileName == null) {
            throw new MyException("FILENAME IS NULL");
        }
        return new ProductsConverter(fileName)
                .fromJson()
                .orElseThrow(() -> new MyException("PRODUCTS CONVERTER IS NULL"));
    }

    public List<UserDto> loadUsers(String fileName) {
        if (fileName == null) {
            throw new MyException("FILENAME IS NULL");
        }
        return new UsersConverter(fileName)
                .fromJson()
                .orElseThrow(() -> new MyException("USER CONVERTER IS NULL"));
    }

    public List<CategoryDto> loadCategories(String fileName) {
        if (fileName == null) {
            throw new MyException("FILENAME IS NULL");
        }
        return new CategoryConverter(fileName)
                .fromJson()
                .orElseThrow(() -> new MyException("CATEGORIES CONVERTER IS NULL"));
    }

    public List<UserProductDto> loadUserProducts(String fileName) {
        if (fileName == null) {
            throw new MyException("FILENAME IS NULL");
        }
        return new UserProductConverter(fileName)
                .fromJson()
                .orElseThrow(() -> new MyException("USER PRODUCTS CONVERTER IS NULL"));
    }

    public List<UserCategoryDto> loadUserCategories(String fileName) {
        if (fileName == null) {
            throw new MyException("FILENAME IS NULL");
        }
        return new UserCategoryConverter(fileName)
                .fromJson()
                .orElseThrow(() -> new MyException("USER CATEGORIES CONVERTER IS NULL"));
    }
}
