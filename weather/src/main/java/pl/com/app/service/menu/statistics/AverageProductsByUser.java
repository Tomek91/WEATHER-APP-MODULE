package pl.com.app.service.menu.statistics;

import org.eclipse.collections.impl.collector.Collectors2;
import pl.com.app.dto.UserProductsWeatherDto;
import pl.com.app.service.MenuService;
import pl.com.app.service.UserProductService;
import pl.com.app.service.menu.IState;
import pl.com.app.service.menu.UserOptions;

import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageProductsByUser implements IState {
    private UserProductService userProductService = new UserProductService();

    @Override
    public boolean change(MenuService menuService) {
        var result = userProductService.findAll()
                .stream()
                .map(x -> new UserProductsWeatherDto(x, userProductService.getWeatherByProductCondition(x.getName())))
                .collect(Collectors.groupingBy(x -> x.getUserProductDto().getUserDto()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> String.join(" ", e.getKey().getName(), e.getKey().getSurname()),
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(UserProductsWeatherDto::getWeatherCondition))
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        ee -> ee.getValue()
                                                .stream()
                                                .collect(Collectors2.summarizingBigDecimal(p -> p.getUserProductDto().getPrice()))
                                                .getAverage()
                                                .setScale(2, RoundingMode.UP)
                                ))
                ));

        System.out.println("AverageProductsByUser");
        result.forEach((k, v) -> System.out.println(k + " " + v));

        menuService.setState(new UserOptions());
        return true;
    }
}
