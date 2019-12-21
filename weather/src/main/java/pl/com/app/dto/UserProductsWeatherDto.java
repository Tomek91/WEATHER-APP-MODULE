package pl.com.app.dto;



import pl.com.app.model.enums.WeatherCondition;

import java.math.BigDecimal;
import java.util.Objects;

public class UserProductsWeatherDto {
    private UserProductDto userProductDto;
    private WeatherCondition weatherCondition;

    public UserProductsWeatherDto() {
    }

    public UserProductsWeatherDto(UserProductDto userProductDto, WeatherCondition weatherCondition) {
        this.userProductDto = userProductDto;
        this.weatherCondition = weatherCondition;
    }

    public UserProductDto getUserProductDto() {
        return userProductDto;
    }

    public UserProductsWeatherDto setUserProductDto(UserProductDto userProductDto) {
        this.userProductDto = userProductDto;
        return this;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public UserProductsWeatherDto setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProductsWeatherDto that = (UserProductsWeatherDto) o;
        return Objects.equals(userProductDto, that.userProductDto) &&
                weatherCondition == that.weatherCondition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProductDto, weatherCondition);
    }

    @Override
    public String toString() {
        return "UserProductsWeatherDto{" +
                "userProductDto=" + userProductDto +
                ", weatherCondition=" + weatherCondition +
                '}';
    }
}
