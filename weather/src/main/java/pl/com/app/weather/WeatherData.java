package pl.com.app.weather;

import java.util.List;
import java.util.Objects;

public class WeatherData {

    private List<Weather> weather;
    private Main main;

    public WeatherData() {
    }

    public WeatherData(List<Weather> weather, Main main) {
        this.weather = weather;
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public WeatherData setWeather(List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    public Main getMain() {
        return main;
    }

    public WeatherData setMain(Main main) {
        this.main = main;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData weatherData = (WeatherData) o;
        return Objects.equals(weather, weatherData.weather) &&
                Objects.equals(main, weatherData.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weather, main);
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "weather=" + weather +
                ", main=" + main +
                '}';
    }
}
