package pl.com.app.dto;


import java.math.BigDecimal;
import java.util.Objects;

public class ProductModelDto {
    private String name;
    private BigDecimal price;
    private UserCategoryDto category;

    public ProductModelDto() {
    }

    public ProductModelDto(String name, BigDecimal price, UserCategoryDto userCategoryDto) {
        this.name = name;
        this.price = price;
        this.category = userCategoryDto;
    }

    public String getName() {
        return name;
    }

    public ProductModelDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductModelDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserCategoryDto getCategory() {
        return category;
    }

    public ProductModelDto setCategory(UserCategoryDto category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModelDto that = (ProductModelDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category);
    }

    @Override
    public String toString() {
        return "ProductModelDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
