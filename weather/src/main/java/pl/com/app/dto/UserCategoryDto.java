package pl.com.app.dto;



import java.util.Objects;

public class UserCategoryDto {
    private Long id;
    private String name;

    public UserCategoryDto() {
    }

    public UserCategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public UserCategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCategoryDto that = (UserCategoryDto) o;
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
