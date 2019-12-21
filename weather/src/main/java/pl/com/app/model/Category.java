package pl.com.app.model;


import pl.com.app.model.enums.WeatherCondition;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "weatherConditions",
            joinColumns = @JoinColumn(name = "category_id")
    )
    @Column(name = "weatherConditions")
    @Enumerated(EnumType.STRING)
    private Set<WeatherCondition> weatherConditions;

    @OneToMany(mappedBy = "category")
    Set<Product> products;

    public Category() {
    }


    public Category(Long id, String name, Set<WeatherCondition> weatherConditions, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.weatherConditions = weatherConditions;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(Set<WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(weatherConditions, category.weatherConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weatherConditions);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weatherConditions=" + weatherConditions +
                '}';
    }
}
