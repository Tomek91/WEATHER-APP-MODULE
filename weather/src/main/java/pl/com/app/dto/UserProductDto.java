package pl.com.app.dto;



import java.math.BigDecimal;
import java.util.Objects;

public class UserProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private UserCategoryDto userCategoryDto;
    private UserDto userDto;

    public UserProductDto() {
    }

    public UserProductDto(Long id, String name, BigDecimal price, UserCategoryDto userCategoryDto, UserDto userDto) {
        this(name, price, userCategoryDto, userDto);
        this.id = id;
    }

    public UserProductDto(String name, BigDecimal price, UserCategoryDto userCategoryDto, UserDto userDto) {
        this.name = name;
        this.price = price;
        this.userCategoryDto = userCategoryDto;
        this.userDto = userDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProductDto that = (UserProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(userCategoryDto, that.userCategoryDto) &&
                Objects.equals(userDto, that.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, userCategoryDto, userDto);
    }

    @Override
    public String toString() {
        return "UserProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", userCategory=" + userCategoryDto +
                ", user=" + userDto +
                '}';
    }

    public Long getId() {
        return id;
    }

    public UserProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserCategoryDto getUserCategoryDto() {
        return userCategoryDto;
    }

    public UserProductDto setUserCategoryDto(UserCategoryDto userCategoryDto) {
        this.userCategoryDto = userCategoryDto;
        return this;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public UserProductDto setUserDto(UserDto userDto) {
        this.userDto = userDto;
        return this;
    }
}
