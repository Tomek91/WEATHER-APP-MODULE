package pl.com.app.service.menu.statistics;

import pl.com.app.service.MenuService;
import pl.com.app.service.UserProductService;
import pl.com.app.service.menu.IState;
import pl.com.app.service.menu.UserOptions;

import java.util.Map;
import java.util.stream.Collectors;

public class CategoriesDependOnAge implements IState {
    private UserProductService userProductService = new UserProductService();

    @Override
    public boolean change(MenuService menuService) {
        Map<Integer, String> categoriesDependOnAge = userProductService.findAll()
                .stream()
                .collect(Collectors.groupingBy(x -> x.getUserDto().getAge()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(x -> x.getUserCategoryDto().getName(), Collectors.counting()))
                                .entrySet()
                                .stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElseThrow(NullPointerException::new)
                ));

        System.out.println("CategoriesDependOnAge");
        categoriesDependOnAge.forEach((k, v) -> System.out.println(k + " " + v));

        menuService.setState(new UserOptions());
        return true;
    }
}
