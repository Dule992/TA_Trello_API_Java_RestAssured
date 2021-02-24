package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By loginButton = By.xpath("//a [@class=\"btn btn-lg btn-secondary\"]");
    private By emailFiled = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By submitButton = By.xpath("//button[@data-testid=\"submit_btn\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username)
    {
        type(username, emailFiled);
    }

    public void setPassword(String password)
    {
        type(password, passwordField);
    }

    public LoginPage loginWith(String username, String password)
    {
        waitForVisibiltyLocator(loginButton);

        click(loginButton);
        setUsername(username);
        setPassword(password);
        click(submitButton);

        return new LoginPage(driver);
    }


}
