package pl.com.app.json;

import pl.com.app.dto.UserProductDto;

import java.util.List;

public class UserProductConverter extends JsonConverter<List<UserProductDto>> {
    public UserProductConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
