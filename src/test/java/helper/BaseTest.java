package helper;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Parser;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {
    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().goTo(Parser.parseConfig("baseUrl"));
        getBrowser().maximize();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}