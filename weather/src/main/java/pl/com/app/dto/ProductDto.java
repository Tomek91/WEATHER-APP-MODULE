package pl.com.app.dto;


import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private CategoryDto categoryDto;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, BigDecimal price, CategoryDto categoryDto) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryDto = categoryDto;
    }

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public ProductDto setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
        return this;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryDto=" + categoryDto +
                '}';
    }
}
