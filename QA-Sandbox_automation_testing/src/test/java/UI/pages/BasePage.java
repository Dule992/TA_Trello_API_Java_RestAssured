package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    private By submitButton = By.xpath("//button[contains(text(),'Submit')]");

    public BasePage(WebDriver driver){

        this.driver = driver;
    }

    protected WebElement find(By locator)
    {
        return driver.findElement(locator);
    }


    protected void type(String text, By locator)
    {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected void click(By locator)
    {
        find(locator).click();
    }

    protected void clickOnSubmit()
    {
        click(submitButton);
    }

    protected boolean isDisplayed(By locator)
    {
        try
        {
            return find(locator).isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    protected void waitForVisibiltyLocator(By locator)
    {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForVisibitlityWebElement(WebElement element)
    {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
