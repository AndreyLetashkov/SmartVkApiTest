package forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.Parser;

public class PostForm extends Form {
    private final String id;

    public PostForm(String id) {
        super(By.xpath(String.format("//div[@id='post%s_%s']", Parser.parseTestData("ownerId"), id)), "Post");
        this.id = id;
    }

    public String getPhotoLink(String attribute) {
        String link = getElementFactory().getLink(By.xpath(String.format("//div[contains(@id, '%s')]//a[@aria-label='photo']", id)), "Photo").getAttribute(attribute);
        return link.substring(link.indexOf("https"));
    }

    public void openComments() {
        getElementFactory().getLink(By.xpath(String.format("//div[contains(@class, 'replies_wrap')]//a[contains(@href, '%s')]", id)), "Comment")
                .clickAndWait();
    }

    public String getTextFromPost() {
        ILabel text = getElementFactory().getLabel(
                By.xpath(String.format("//div[contains(@id, %s)]//div[contains(@class, 'post_text')]", id)), "Post");
        if (text.state().waitForDisplayed()) {
            return getElementFactory().getLabel(
                    By.xpath(String.format("//div[contains(@id, %s)]//div[contains(@class, 'post_text')]", id)), "Post").getText();
        }
        return null;
    }

    public String getAuthorFromPost(String id, String attribute) {
        return getElementFactory().getLabel(
                By.xpath(String.format("//div[contains(@id, '%s')]//a[@class='author']", id)), "Author")
                .getAttribute(attribute);
    }

    public boolean isPostDeleted() {
        return !getElementFactory().getLabel(By.xpath(String.format("//div[contains(@id, '%s')]//div[contains(@class, 'wall_reply_text')]", id)), "Post").state().isDisplayed();
    }

    public String getTextFromComment(String id) {
        return getElementFactory().getLabel(
                By.xpath(String.format("//div[contains(@id, '%s')]//div[contains(@class, 'wall_reply_text')]", id)), "Comment")
                .getText();
    }

    public void likePost() {
        getElementFactory().getButton(By.xpath(String.format("//div[contains(@id, '%s')]//div[contains(@class, '__icon')]", id)), "Like").clickAndWait();
    }
}