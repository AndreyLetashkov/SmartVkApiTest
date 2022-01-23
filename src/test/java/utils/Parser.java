package utils;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
    public static String parseTestData(String key) {
        JSONParser parser = new JSONParser();
        String value = null;
        try (FileReader reader = new FileReader("./src/test/java/testData/testData.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            value = (String) jsonObject.get(key);
        } catch (IOException | ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String parseConfig(String key) {
        JSONParser parser = new JSONParser();
        String value = null;
        try (FileReader reader = new FileReader("./src/main/resources/config.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            value = (String) jsonObject.get(key);
        } catch (IOException | ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
}