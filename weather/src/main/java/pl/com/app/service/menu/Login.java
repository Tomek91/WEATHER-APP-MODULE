package pl.com.app.service.menu;

import pl.com.app.dto.UserDto;
import pl.com.app.reader.DataReader;
import pl.com.app.service.MenuService;
import pl.com.app.service.UserService;
import pl.com.app.service.menu.login.UserLoginOptions;

import java.util.Optional;

public class Login implements IState {

    private DataReader dataReader = new DataReader();
    private UserService userService = new UserService();

    @Override
    public boolean change(MenuService menuService) {

        System.out.println("Podaj hasło:");
        String password = dataReader.getString();

        Optional<UserDto> userDtoOpt = userService.findByPassword(password);

        if (userDtoOpt.isPresent()){
            menuService.setState(new UserLoginOptions());
            menuService.setLoginUser(userDtoOpt.get());
        } else {
            System.out.println("Niepoprawne hasło");
            menuService.setState(new UserOptions());
        }

        return true;
    }
}
