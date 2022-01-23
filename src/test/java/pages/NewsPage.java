package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {
    private final ILink downloadsLink = getElementFactory().getLink(By.xpath("//li[@id='l_pr']//span[contains(@class, 'left_label')]"), "My page");

    public NewsPage() {
        super(By.xpath("//div[contains(@class, 'feed_title')]"), "Catalog of stories");
    }

    public void navigateMyPage() {
        downloadsLink.clickAndWait();
    }
}