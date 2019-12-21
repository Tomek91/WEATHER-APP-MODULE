package pl.com.app.service.menu.login;

import pl.com.app.service.MenuService;
import pl.com.app.service.UserProductService;
import pl.com.app.service.WeatherService;
import pl.com.app.service.menu.IState;


public class WeatherWithApiProducts implements IState {
    private WeatherService weatherService = new WeatherService();
    private UserProductService userProductService = new UserProductService();

    @Override
    public boolean change(MenuService menuService) {
        var weatherByCity = weatherService.getWeatherAndProductApi(menuService.getLoginUser().getCity());

        weatherByCity.thenAccept(res -> {
            System.out.println(res);
            userProductService.saveUserProducts(res, menuService.getLoginUser());
        });

        menuService.setState(new UserLoginOptions());
        return true;
    }
}
