package pl.com.app.service.menu.statistics;

import pl.com.app.dto.ProductModelDto;
import pl.com.app.model.enums.Gender;
import pl.com.app.service.MenuService;
import pl.com.app.service.UserProductService;
import pl.com.app.service.menu.IState;
import pl.com.app.service.menu.UserOptions;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductsDependOnGender implements IState {
    private UserProductService userProductService = new UserProductService();

    @Override
    public boolean change(MenuService menuService) {
        var productsDependOnGender = userProductService.findAll()
                .stream()
                .collect(Collectors.groupingBy(x -> x.getUserDto().getGender()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .map(x -> new ProductModelDto(x.getName(), x.getPrice(), x.getUserCategoryDto()))
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .map(Map.Entry::getKey)
                                .map(ProductModelDto::getName)
                                .collect(Collectors.toList())
                ));

        System.out.println("ProductsDependOnGender");
        productsDependOnGender.forEach((k, v) -> System.out.println(k + " " + v));

        menuService.setState(new UserOptions());
        return true;
    }
}
