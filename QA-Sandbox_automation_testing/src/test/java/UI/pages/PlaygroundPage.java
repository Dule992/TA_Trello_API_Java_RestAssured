package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaygroundPage extends BasePage{

    private By peoplePage = By.xpath("//div[contains(text(),'PEOPLE')]");
    private By technologiesPage = By.xpath("//div[contains(text(),'TECHNOLOGIES')]");
    private By senioritiesPage = By.xpath("//div[contains(text(),'SENIORITIES')]");
    private By createPersonButton = By.xpath("//span[contains(text(),'CREATE PERSON')]");

    public PlaygroundPage(WebDriver driver) {
        super(driver);
    }

    public PlaygroundPage clickOnPeoplePage()
    {
        waitForVisibiltyLocator(peoplePage);

        click(peoplePage);

        waitForVisibiltyLocator(createPersonButton);

        return new PlaygroundPage(driver);
    }

    public PlaygroundPage clickOnTechnologies()
    {
        waitForVisibiltyLocator(technologiesPage);

        click(technologiesPage);

        return new PlaygroundPage(driver);
    }

    public PlaygroundPage clickOnSeniorities()
    {
        waitForVisibiltyLocator(senioritiesPage);

        click(senioritiesPage);

        return new PlaygroundPage(driver);
    }



}
