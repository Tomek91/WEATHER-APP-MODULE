package pl.com.app.service.menu.login;

import pl.com.app.service.menu.IState;
import pl.com.app.service.MenuService;
import pl.com.app.service.menu.UserOptions;

public class Logout implements IState {

    @Override
    public boolean change(MenuService menuService) {
        menuService.setLoginUser(null);
        menuService.setState(new UserOptions());
        return true;
    }
}
