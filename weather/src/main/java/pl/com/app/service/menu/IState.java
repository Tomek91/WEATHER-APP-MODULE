package pl.com.app.service.menu;

import pl.com.app.service.MenuService;

@FunctionalInterface
public interface IState {
    boolean change(MenuService menuService);
}
