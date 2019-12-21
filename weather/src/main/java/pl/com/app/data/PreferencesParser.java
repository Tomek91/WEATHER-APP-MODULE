package pl.com.app.data;


import pl.com.app.exception.MyException;
import pl.com.app.model.enums.Preferences;

public class PreferencesParser implements Parser<Preferences> {
    @Override
    public Preferences parse(String line) {
        Preferences value;
        try {
            value = Preferences.valueOf(line);
        } catch (Exception e) {
            throw new MyException("PREFERENCES PARSE EXCEPTION");
        }
        return value;
    }
}
