package pl.com.app.model;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_category")
public class UserCategory {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "userCategory", cascade = CascadeType.PERSIST)
    private Set<UserProduct> userProducts;

    public UserCategory() {
    }

    public UserCategory(Long id, String name, Set<UserProduct> userProducts) {
        this.id = id;
        this.name = name;
        this.userProducts = userProducts;
    }

    public Long getId() {
        return id;
    }

    public UserCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserCategory setName(String name) {
        this.name = name;
        return this;
    }

    public Set<UserProduct> getUserProducts() {
        return userProducts;
    }

    public UserCategory setUserProducts(Set<UserProduct> userProducts) {
        this.userProducts = userProducts;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCategory that = (UserCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
