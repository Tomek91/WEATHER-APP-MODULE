package pl.com.app.service.menu;

import pl.com.app.service.MenuService;

public class AppEnd implements IState {

    @Override
    public boolean change(MenuService menuService) {
        System.out.println("Koniec programu");
        return false;
    }
}
