package pl.com.app.data;


import pl.com.app.exception.MyException;
import pl.com.app.model.enums.Gender;

public class GenderParser implements Parser<Gender> {
    @Override
    public Gender parse(String line) {
        Gender value;
        try {
            value = Gender.valueOf(line);
        } catch (Exception e) {
            throw new MyException("GENDER PARSE EXCEPTION");
        }
        return value;
    }
}
