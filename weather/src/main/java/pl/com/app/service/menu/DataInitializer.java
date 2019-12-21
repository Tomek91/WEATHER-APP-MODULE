package pl.com.app.service.menu;

import pl.com.app.dto.*;
import pl.com.app.json.FileNames;
import pl.com.app.model.User;
import pl.com.app.service.*;

import java.util.List;


public class DataInitializer implements IState {
    private DataLoaderService dataLoaderService = new DataLoaderService();
    private UserService userService = new UserService();
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private UserProductService userProductService = new UserProductService();
    private UserCategoryService userCategoryService = new UserCategoryService();

    @Override
    public boolean change(MenuService menuService) {
        userProductService.deleteAll();
        userCategoryService.deleteAll();
        productService.deleteAll();
        userService.deleteAll();
        categoryService.deleteAll();

        List<ProductDto> productDtoList = dataLoaderService.loadProducts(FileNames.PRODUCTS);
        List<UserDto> userDtoList = dataLoaderService.loadUsers(FileNames.USERS);
        List<CategoryDto> categoryDtoList = dataLoaderService.loadCategories(FileNames.CATEGORIES);
        List<UserProductDto> userProductDtoList = dataLoaderService.loadUserProducts(FileNames.USER_PRODUCTS);
        List<UserCategoryDto> userCategoryDtoList = dataLoaderService.loadUserCategories(FileNames.USER_CATEGORIES);

        categoryService.addAll(categoryDtoList);
        productService.addAll(productDtoList);
        userService.addAll(userDtoList);
        userCategoryService.addAll(userCategoryDtoList);
        userProductService.addAll(userProductDtoList);

        menuService.setState(new UserOptions());
        return true;
    }
}
