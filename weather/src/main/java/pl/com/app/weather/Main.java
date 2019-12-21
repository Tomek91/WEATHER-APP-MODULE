
package pl.com.app.weather;


import java.util.Objects;

public class Main {
    private Long humidity;
    private Long pressure;
    private Double temp;
    private Double temp_max;
    private Double temp_min;

    public Main() {
    }

    public Main(Long humidity, Long pressure, Double temp, Double temp_max, Double temp_min) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public Long getHumidity() {
        return humidity;
    }

    public Main setHumidity(Long humidity) {
        this.humidity = humidity;
        return this;
    }

    public Long getPressure() {
        return pressure;
    }

    public Main setPressure(Long pressure) {
        this.pressure = pressure;
        return this;
    }

    public Double getTemp() {
        return temp;
    }

    public Main setTemp(Double temp) {
        this.temp = temp;
        return this;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public Main setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
        return this;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Main setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main main = (Main) o;
        return Objects.equals(humidity, main.humidity) &&
                Objects.equals(pressure, main.pressure) &&
                Objects.equals(temp, main.temp) &&
                Objects.equals(temp_max, main.temp_max) &&
                Objects.equals(temp_min, main.temp_min);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humidity, pressure, temp, temp_max, temp_min);
    }

    @Override
    public String toString() {
        return "Main{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", temp=" + temp +
                ", temp_max=" + temp_max +
                ", temp_min=" + temp_min +
                '}';
    }
}
