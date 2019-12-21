package pl.com.app.dto;

import pl.com.app.model.enums.Gender;
import pl.com.app.model.enums.Preferences;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String city;
    private String email;
    private Gender gender;
    private Integer age;
    private Preferences preferences;


    public UserDto() {
    }

    public UserDto(Long id, String name, String surname, String password, String city, String email, Gender gender, Integer age, Preferences preferences) {
        this(name, surname, password, city, email, gender, age, preferences);
        this.id = id;
    }

    public UserDto(String name, String surname, String password, String city, String email, Gender gender, Integer age, Preferences preferences) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.city = city;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.preferences = preferences;
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public UserDto setPreferences(Preferences preferences) {
        this.preferences = preferences;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserDto setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(city, userDto.city) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(age, userDto.age) &&
                gender == userDto.gender &&
                preferences == userDto.preferences;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, password, city, email, gender, age, preferences);
    }

    @Override
    public String toString() {
        return "UserDto{" +
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
}
