package pl.com.app.service.menu;

import pl.com.app.reader.DataReader;
import pl.com.app.service.MenuService;
import pl.com.app.service.menu.statistics.AverageProductsByUser;
import pl.com.app.service.menu.statistics.CategoriesDependOnAge;
import pl.com.app.service.menu.statistics.MostCommonProductsByWeather;
import pl.com.app.service.menu.statistics.ProductsDependOnGender;

public class UserOptions implements IState {

    private DataReader dataReader = new DataReader();

    @Override
    public boolean change(MenuService menuService) {
        System.out.println("1\t-\tDodaj uzytkownika");
        System.out.println("2\t-\tZaloguj sie");
        System.out.println("3\t-\tPokaz wszystkie produkty");
        System.out.println("4\t-\tPokaz wszystkich uzytkownikow");
        System.out.println("5\t-\tZaładuj dane domyślne");
        System.out.println("6\t-\tPogrupować najpopularniejsze kategorie według wieku");
        System.out.println("7\t-\tPogrupować najchętniej kupowane produkty w zależnosci od płci czyli dla każdej płci posortować kolejne produkty według popularności");
        System.out.println("8\t-\tWypisać które produkty najchętniej polecano dla danej pogody");
        System.out.println("9\t-\tŚrednia cena produktów dla każdego usera pogrupowane po pogodzie");
        System.out.println("q\t-\tKoniec");

        String option = dataReader.getString();

        switch (option){
            case "1":
                menuService.setState(new AddNewUser());
                break;
            case "2":
                menuService.setState(new Login());
                break;
            case "3":
                menuService.setState(new ShowProducts());
                break;
            case "4":
                menuService.setState(new ShowUsers());
                break;
            case "5":
                menuService.setState(new DataInitializer());
                break;
            case "6":
                menuService.setState(new CategoriesDependOnAge());
                break;
            case "7":
                menuService.setState(new ProductsDependOnGender());
                break;
            case "8":
                menuService.setState(new MostCommonProductsByWeather());
                break;
            case "9":
                menuService.setState(new AverageProductsByUser());
                break;
            case "q":
                menuService.setState(new AppEnd());
                break;
            default:
                System.out.println("Niewłaściwa wartość");
                menuService.setState(new UserOptions());
                break;
        }
        return true;
    }
}
