package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TechnologyPage extends BasePage{

    private By createTechnologyButton = By.xpath("//span[contains(text(),'CREATE TECHNOLOGY')]");
    private By technologyTitle = By.xpath("//input[@name=\"technology_title\"]");

    public TechnologyPage(WebDriver driver) {
        super(driver);
    }


    public void createTechnology(String technology)
    {
        click(createTechnologyButton);
        type(technology, technologyTitle);
        clickOnSubmit();
    }

    public boolean isTechnologyCreated(String technology){

        By technologyDetails = By.xpath("//a[contains(text(),'" + technology + "')]");

        waitForVisibiltyLocator(technologyDetails);

        return isDisplayed(technologyDetails);
    }
}
