package pl.com.app.service.menu;

import pl.com.app.dto.UserDto;
import pl.com.app.model.enums.Gender;
import pl.com.app.model.enums.Preferences;
import pl.com.app.reader.DataReader;
import pl.com.app.service.MenuService;
import pl.com.app.service.UserService;

public class AddNewUser implements IState {

    private DataReader dataReader = new DataReader();
    private UserService userService = new UserService();

    @Override
    public boolean change(MenuService menuService) {

        System.out.println("Podaj imie:");
        String name = dataReader.getString();

        System.out.println("Podaj nazwisko:");
        String surname = dataReader.getString();

        System.out.println("Podaj hasło:");
        String password = dataReader.getString();

        System.out.println("Podaj miasto:");
        String city = dataReader.getString();

        System.out.println("Podaj email:");
        String email = dataReader.getString();

        System.out.println("Podaj wiek:");
        Integer age = dataReader.getInteger();

        System.out.println("Podaj płeć:");
        Gender gender = dataReader.getGender();

        System.out.println("Podaj kryterium sortowania produktów, oto dostępne możliwości:");
        for (Preferences p : Preferences.values()){
            System.out.println(p);
        }

        try{
            Preferences preferences = dataReader.getPreferences();
            userService.addUser(new UserDto(name, surname, password, city, email, gender, age, preferences));
            System.out.println("Poprawnie dodano user'a");
        } catch (Exception e) {
            System.err.println("BŁĄD");
            System.err.println(e.getMessage());
        }
        menuService.setState(new UserOptions());

        return true;
    }
}


