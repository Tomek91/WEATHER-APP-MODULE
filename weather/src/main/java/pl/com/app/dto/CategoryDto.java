package pl.com.app.dto;


import pl.com.app.model.enums.WeatherCondition;

import java.util.Set;

public class CategoryDto {
    private Long id;
    private String name;
    private Set<WeatherCondition> weatherConditions;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, Set<WeatherCondition> weatherConditions) {
        this.id = id;
        this.name = name;
        this.weatherConditions = weatherConditions;
    }

    public Long getId() {
        return id;
    }

    public CategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    public CategoryDto setWeatherConditions(Set<WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
