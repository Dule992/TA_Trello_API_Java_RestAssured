package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeniorityPage extends BasePage{

    private By createSeniorityButton = By.xpath("//span[contains(text(),'CREATE SENIORITY')]");
    private By seniorityTitle = By.xpath("//input[@name=\"seniority_title\"]");


    public SeniorityPage(WebDriver driver) {
        super(driver);
    }

    public SeniorityPage createSeniority(String seniority)
    {
        click(createSeniorityButton);
        type(seniority, seniorityTitle);
        clickOnSubmit();

        return new SeniorityPage(driver);
    }

    public boolean isSeniorityCreated(String seniority){

        By seniorityDetails = By.xpath("//a[contains(text(),'" + seniority + "')]");

        waitForVisibiltyLocator(seniorityDetails);

        return isDisplayed(seniorityDetails);
    }

}
