package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthenticationPage extends Form {
    private final IButton btnAuthentication = getElementFactory().getButton(By.xpath("//button[@id='index_login_button']"),"Login");
    private final ITextBox txbEmail = getElementFactory().getTextBox(By.xpath("//input[@id='index_email']"),"Email");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//input[@id='index_pass']"),"Password");

    public AuthenticationPage() {
        super(By.xpath("//button[@id='index_login_button']"), "Login");
    }

    public void authentication(String email, String password) {
        txbEmail.clearAndType(email);
        txbPassword.clearAndType(password);
        btnAuthentication.clickAndWait();
    }
}