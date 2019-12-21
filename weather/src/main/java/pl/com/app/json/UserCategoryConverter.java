package pl.com.app.json;

import pl.com.app.dto.UserCategoryDto;

import java.util.List;

public class UserCategoryConverter extends JsonConverter<List<UserCategoryDto>> {
    public UserCategoryConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
