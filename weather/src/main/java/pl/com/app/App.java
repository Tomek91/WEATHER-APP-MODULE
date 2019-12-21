package pl.com.app;

import pl.com.app.exception.MyException;
import pl.com.app.service.MenuService;
import pl.com.app.service.ProductHttpService;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            new MenuService().doWeatherApp();
        } catch (MyException e) {
            System.err.println(e.toString());
        }
    }
}
