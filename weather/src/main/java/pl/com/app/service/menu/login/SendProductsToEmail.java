package pl.com.app.service.menu.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.com.app.dto.ProductDto;
import pl.com.app.email.EmailUtil;
import pl.com.app.service.ProductService;
import pl.com.app.service.UserService;
import pl.com.app.service.WeatherService;
import pl.com.app.service.menu.IState;
import pl.com.app.service.MenuService;
import pl.com.app.weather.WeatherData;

import java.util.List;
import java.util.stream.Collectors;

public class SendProductsToEmail implements IState {
    private WeatherService weatherService = new WeatherService();
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    @Override
    public boolean change(MenuService menuService) {

        var weatherByCity = weatherService.getWeatherByCity(menuService.getLoginUser().getCity());

        weatherByCity.thenAccept(res -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            WeatherData weatherDataFromJson = gson.fromJson(res.body(), WeatherData.class);
            System.out.println("***** DATA FROM REQUEST *****");
            List<ProductDto> productDtoList = productService.findProductsByWeatherCondition(weatherDataFromJson.getWeather());
            productDtoList = userService.sortByUserPreferences(productDtoList, menuService.getLoginUser().getPreferences());
            EmailUtil.send(
                    menuService.getLoginUser().getEmail(),
                    "Products depends on weather conditions",
                    productDtoList.stream().map(ProductDto::toString).collect(Collectors.joining("\n"))
            );
            System.out.println("***** ***** ***** ***** *****");
        });

        menuService.setState(new UserLoginOptions());
        return true;
    }
}
