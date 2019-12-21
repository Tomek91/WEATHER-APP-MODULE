package pl.com.app.service.menu.login;

import pl.com.app.reader.DataReader;
import pl.com.app.service.menu.IState;
import pl.com.app.service.MenuService;

public class UserLoginOptions implements IState {

    private DataReader dataReader = new DataReader();

    @Override
    public boolean change(MenuService menuService) {
        System.out.println("1\t-\tPobierz pogode w moim mieście");
        System.out.println("2\t-\tWypisz produkty, które moge kupić");
        System.out.println("3\t-\tWyślij na maila produkty, które moge kupić");
        System.out.println("4\t-\tPobierz pogodę oraz produkty z api i zapisz wybrane do bazy danych");
        System.out.println("o\t-\tWyloguj");

        String option = dataReader.getString();

        switch (option){
            case "1":
                menuService.setState(new CheckWeather());
                break;
            case "2":
                menuService.setState(new ShowProductsToUserBuy());
                break;
            case "3":
                menuService.setState(new SendProductsToEmail());
                break;
            case "4":
                menuService.setState(new WeatherWithApiProducts());
                break;
            case "o":
                menuService.setState(new Logout());
                break;
            default:
                System.out.println("Niewłaściwa wartość");
                menuService.setState(new UserLoginOptions());
                break;
        }
        return true;
    }
}
