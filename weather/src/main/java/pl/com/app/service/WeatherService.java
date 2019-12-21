package pl.com.app.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.com.app.dto.ProductModelDto;
import pl.com.app.dto.WeatherProductsDto;
import pl.com.app.exception.MyException;
import pl.com.app.requests.AsyncRequest;
import pl.com.app.weather.WeatherData;

import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WeatherService {
    private static final String weatherPath = "http://api.openweathermap.org/data/2.5/weather?q=__city__&APPID=";
    private ProductHttpService productHttpService = new ProductHttpService();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public CompletableFuture<HttpResponse<String>> getWeatherByCity(final String city) {
        CompletableFuture<HttpResponse<String>> response = null;
        try {
            if (city == null) {
                throw new NullPointerException("CITY IS NULL");
            }

            response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .sendAsync(
                            AsyncRequest.requestGet(weatherPath.replace("__city__", city)),
                            HttpResponse.BodyHandlers.ofString()
                    );

        } catch (Exception e) {
            throw new MyException("WEATHER SERVICE: " + e.getMessage());
        }
        return response;
    }

    public CompletableFuture<WeatherProductsDto> getWeatherAndProductApi(String city) {
        CompletableFuture<WeatherProductsDto> response = null;
        try {
            if (city == null) {
                throw new NullPointerException("CITY IS NULL");
            }

            var x1 = this.getWeatherByCity(city);
            var x2 = productHttpService.getProducts();

            response = x1
                    .thenCombine(x2, (res1, res2) -> {
                        WeatherData weatherDataFromJson = gson.fromJson(res1.body(), WeatherData.class);
                        List<ProductModelDto> productModelDto = gson.fromJson(res2.body(), new TypeToken<ArrayList<ProductModelDto>>() {}.getType());
                        return new WeatherProductsDto(weatherDataFromJson, productModelDto);
                    });

        } catch (Exception e) {
            throw new MyException("WEATHER SERVICE: " + e.getMessage());
        }
        return response;
    }
}
