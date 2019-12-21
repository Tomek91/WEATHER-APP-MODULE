package pl.com.app.mappers;

import pl.com.app.dto.*;
import pl.com.app.model.*;

import java.util.HashSet;

public class ModelMapper {

    public CategoryDto fromCategoryToCategoryDto(Category category){
        return category == null ? null : new CategoryDto(category.getId(), category.getName(), category.getWeatherConditions());
    }

    public Category fromCategoryDtoToCategory(CategoryDto categoryDto){
        return categoryDto == null ? null : new Category(categoryDto.getId(), categoryDto.getName(), categoryDto.getWeatherConditions(), new HashSet<>());
    }

    public UserDto fromUserToUserDto(User user){
        return user == null ? null : new UserDto(user.getId(), user.getName(), user.getSurname(), user.getPassword(), user.getCity(), user.getEmail(), user.getGender(), user.getAge(), user.getPreferences());

    }

    public User fromUserDtoToUser(UserDto userDto){
        return userDto == null ? null : new User(userDto.getId(), userDto.getName(), userDto.getSurname(), userDto.getPassword(), userDto.getCity(), userDto.getEmail(), userDto.getPreferences(), userDto.getGender(), userDto.getAge(), new HashSet<>());

    }

    public ProductDto fromProductToProductDto(Product product){
        return product == null ? null : new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getCategory() == null ? null : fromCategoryToCategoryDto(product.getCategory()));

    }

    public Product fromProductDtoToProduct(ProductDto productDto){
        return productDto == null ? null : new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getCategoryDto() == null ? null : fromCategoryDtoToCategory(productDto.getCategoryDto()));

    }

    public UserCategoryDto fromUserCategoryToUserCategoryDto(UserCategory userCategory){
        return userCategory == null ? null : new UserCategoryDto(userCategory.getId(), userCategory.getName());
    }

    public UserCategory fromUserCategoryDtoToUserCategory(UserCategoryDto userCategoryDto){
        return userCategoryDto == null ? null : new UserCategory(userCategoryDto.getId(), userCategoryDto.getName(), new HashSet<>());
    }

    public UserProductDto fromUserProductToUserProductDto(UserProduct userProduct){
        return userProduct == null ? null : new UserProductDto(userProduct.getId(), userProduct.getName(), userProduct.getPrice(), userProduct.getUserCategory() == null ? null : fromUserCategoryToUserCategoryDto(userProduct.getUserCategory()), userProduct.getUser() == null ? null : fromUserToUserDto(userProduct.getUser()));

    }

    public UserProduct fromUserProductDtoToUserProduct(UserProductDto userProductDto){
        return userProductDto == null ? null : new UserProduct(userProductDto.getId(), userProductDto.getName(), userProductDto.getPrice(), userProductDto.getUserCategoryDto() == null ? null : fromUserCategoryDtoToUserCategory(userProductDto.getUserCategoryDto()), userProductDto.getUserDto() == null ? null : fromUserDtoToUser(userProductDto.getUserDto()));

    }
}
