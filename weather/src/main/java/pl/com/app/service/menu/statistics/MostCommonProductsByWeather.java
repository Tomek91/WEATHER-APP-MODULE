package pl.com.app.service.menu.statistics;

import pl.com.app.dto.UserProductsWeatherDto;
import pl.com.app.model.enums.WeatherCondition;
import pl.com.app.service.MenuService;
import pl.com.app.service.UserProductService;
import pl.com.app.service.menu.IState;
import pl.com.app.service.menu.UserOptions;

import java.util.Map;
import java.util.stream.Collectors;

public class MostCommonProductsByWeather implements IState {
    private UserProductService userProductService = new UserProductService();

    @Override
    public boolean change(MenuService menuService) {
        var result = userProductService.findAll()
                .stream()
                .map(x -> new UserProductsWeatherDto(x, userProductService.getWeatherByProductCondition(x.getName())))
                .collect(Collectors.groupingBy(UserProductsWeatherDto::getWeatherCondition))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(x -> x.getUserProductDto().getName(), Collectors.counting()))
                                .entrySet()
                                .stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElseThrow(NullPointerException::new)
                ));

        System.out.println("MostCommonProductsByWeather");
        result.forEach((k, v) -> System.out.println(k + " " + v));

        menuService.setState(new UserOptions());
        return true;
    }
}
