package pl.com.app.reader;

import pl.com.app.data.GenderParser;
import pl.com.app.data.IntegerParser;
import pl.com.app.data.PreferencesParser;
import pl.com.app.model.enums.Gender;
import pl.com.app.model.enums.Preferences;

import java.util.Scanner;

public class DataReader {
    private static Scanner sc = new Scanner(System.in);

    public static void close() {
        sc.close();
    }

    public String getString() {
        return sc.nextLine();
    }

    public Integer getInteger() {
        return new IntegerParser().parse(sc.nextLine());
    }

    public Gender getGender() {
        return new GenderParser().parse(sc.nextLine());
    }

    public Preferences getPreferences() {
        return new PreferencesParser().parse(getString());
    }
}
