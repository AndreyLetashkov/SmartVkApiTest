package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    private static String randomText;

    public static String setRandomText() {
        randomText = RandomStringUtils.random(
                Integer.parseInt(Parser.parseTestData("numberLetters")),
                true,
                false
        );
        return randomText;
    }

    public static String getRandomText() {
        return randomText;
    }
}