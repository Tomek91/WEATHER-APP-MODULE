package pl.com.app.json;

import pl.com.app.dto.CategoryDto;

import java.util.List;

public class CategoryConverter extends JsonConverter<List<CategoryDto>> {
    public CategoryConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
