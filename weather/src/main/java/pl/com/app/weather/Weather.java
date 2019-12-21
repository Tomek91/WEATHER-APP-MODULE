package pl.com.app.weather;


import java.util.Objects;

public class Weather {
    private String description;
    private String icon;
    private Long id;
    private String main;

    public Weather() {
    }

    public Weather(String description, String icon, Long id, String main) {
        this.description = description;
        this.icon = icon;
        this.id = id;
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public Weather setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Weather setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Weather setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMain() {
        return main;
    }

    public Weather setMain(String main) {
        this.main = main;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(description, weather.description) &&
                Objects.equals(icon, weather.icon) &&
                Objects.equals(id, weather.id) &&
                Objects.equals(main, weather.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, icon, id, main);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", main='" + main + '\'' +
                '}';
    }
}
