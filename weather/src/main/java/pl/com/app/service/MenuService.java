package pl.com.app.service;

import pl.com.app.dto.*;
import pl.com.app.repository.generic.connection.DbConnection;
import pl.com.app.service.menu.IState;
import pl.com.app.service.menu.UserOptions;

public class MenuService {
    private IState state;
    private UserDto loginUser;

    public MenuService() {
        this.state = new UserOptions();
    }

    public void doWeatherApp(){
        while (state.change(this)){
            System.out.println("*****************************************");
        }
        DbConnection.closeEntityManagerFactory();
    }

    public IState getState() {
        return state;
    }

    public MenuService setState(IState state) {
        this.state = state;
        return this;
    }

    public UserDto getLoginUser() {
        return loginUser;
    }

    public MenuService setLoginUser(UserDto loginUser) {
        this.loginUser = loginUser;
        return this;
    }
}
