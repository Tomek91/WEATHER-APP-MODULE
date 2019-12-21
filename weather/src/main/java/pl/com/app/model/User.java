package pl.com.app.model;


import pl.com.app.model.enums.Gender;
import pl.com.app.model.enums.Preferences;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String password;

    private String city;

    private String email;

    @Enumerated(EnumType.STRING)
    private Preferences preferences;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    @OneToMany(mappedBy = "user")
    private Set<UserProduct> userProducts;

    public User() {
    }

    public User(Long id, String name, String surname, String password, String city, String email, Preferences preferences, Gender gender, Integer age, Set<UserProduct> userProducts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.city = city;
        this.email = email;
        this.preferences = preferences;
        this.gender = gender;
        this.age = age;
        this.userProducts = userProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(city, user.city) &&
                Objects.equals(email, user.email) &&
                Objects.equals(age, user.age) &&
                gender == user.gender  &&
                preferences == user.preferences;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, password, city, email, gender, age, preferences);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", preferences=" + preferences +
                '}';
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public User setPreferences(Preferences preferences) {
        this.preferences = preferences;
        return this;
    }

    public Set<UserProduct> getUserProducts() {
        return userProducts;
    }

    public User setUserProducts(Set<UserProduct> userProducts) {
        this.userProducts = userProducts;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }
}
