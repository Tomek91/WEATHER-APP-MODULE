package pl.com.app.service.menu.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.com.app.service.WeatherService;
import pl.com.app.service.menu.IState;
import pl.com.app.service.MenuService;
import pl.com.app.weather.WeatherData;


public class CheckWeather implements IState {
    private WeatherService weatherService = new WeatherService();

    @Override
    public boolean change(MenuService menuService) {
        var weatherByCity = weatherService.getWeatherByCity(menuService.getLoginUser().getCity());

        weatherByCity.thenAccept(res -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            WeatherData weatherDataFromJson = gson.fromJson(res.body(), WeatherData.class);
            System.out.println("***** DATA FROM REQUEST *****");
            System.out.println(weatherDataFromJson);
            System.out.println("***** ***** ***** ***** *****");
        });

        menuService.setState(new UserLoginOptions());
        return true;
    }
}

