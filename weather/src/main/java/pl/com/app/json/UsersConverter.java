package pl.com.app.json;

import pl.com.app.dto.UserDto;

import java.util.List;

public class UsersConverter extends JsonConverter<List<UserDto>> {
    public UsersConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
