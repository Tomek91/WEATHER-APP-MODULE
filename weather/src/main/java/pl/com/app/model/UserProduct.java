package pl.com.app.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "user_product")
public class UserProduct {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_category_id")
    private UserCategory userCategory;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public UserProduct() {
    }

    public UserProduct(Long id, String name, BigDecimal price, UserCategory userCategory, User user) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.userCategory = userCategory;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProduct that = (UserProduct) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(userCategory, that.userCategory) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, userCategory, user);
    }

    @Override
    public String toString() {
        return "UserProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", userCategory=" + userCategory +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public UserProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserProduct setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserProduct setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public UserProduct setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserProduct setUser(User user) {
        this.user = user;
        return this;
    }
}
