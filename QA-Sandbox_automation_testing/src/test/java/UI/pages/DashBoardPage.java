package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends BasePage{

    private By playgroundCard = By.xpath("//div[@data-testid=\"playground_card_id\"]");
    private By logout = By.xpath("//a[contains(text(),'Logout')]");

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public DashBoardPage clickOnPlayground(){

        waitForVisibiltyLocator(playgroundCard);

        click(playgroundCard);

        return new DashBoardPage(driver);
    }

    public void logOut()
    {
        click(logout);
    }
}
