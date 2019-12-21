package pl.com.app.service.menu;

import pl.com.app.service.MenuService;
import pl.com.app.service.ProductService;


public class ShowProducts implements IState {
    private ProductService productService = new ProductService();

    @Override
    public boolean change(MenuService menuService) {
        productService.findAll().forEach(System.out::println);
        menuService.setState(new UserOptions());
        return true;
    }
}
