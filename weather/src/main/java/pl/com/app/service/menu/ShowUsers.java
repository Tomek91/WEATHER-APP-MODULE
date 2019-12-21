package pl.com.app.service.menu;

import pl.com.app.service.MenuService;
import pl.com.app.service.UserService;


public class ShowUsers implements IState {
    private UserService userService = new UserService();

    @Override
    public boolean change(MenuService menuService) {
        userService.findAll().forEach(System.out::println);
        menuService.setState(new UserOptions());
        return true;
    }
}
