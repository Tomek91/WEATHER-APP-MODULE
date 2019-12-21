package pl.com.app.dto;

import pl.com.app.weather.WeatherData;

import java.util.List;
import java.util.Objects;

public class WeatherProductsDto {
    private WeatherData weatherData;
    private List<ProductModelDto> productModelDtoList;

    public WeatherProductsDto() {
    }

    public WeatherProductsDto(WeatherData weatherData, List<ProductModelDto> productModelDtoList) {
        this.weatherData = weatherData;
        this.productModelDtoList = productModelDtoList;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public WeatherProductsDto setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
        return this;
    }

    public List<ProductModelDto> getProductModelDtoList() {
        return productModelDtoList;
    }

    public WeatherProductsDto setProductModelDtoList(List<ProductModelDto> productModelDtoList) {
        this.productModelDtoList = productModelDtoList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherProductsDto that = (WeatherProductsDto) o;
        return Objects.equals(weatherData, that.weatherData) &&
                Objects.equals(productModelDtoList, that.productModelDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherData, productModelDtoList);
    }

    @Override
    public String toString() {
        return "WeatherProductsDto{" +
                "weatherData=" + weatherData +
                ", productModelDtoList=" + productModelDtoList +
                '}';
    }
}
