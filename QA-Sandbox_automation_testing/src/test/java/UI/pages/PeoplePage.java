package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PeoplePage extends BasePage {

    private String firstPartOfDropDownXpath = "//input[@name='";
    private String lastPartOfDropDownXpath = "']//parent::div[1]";

    private By createPersonButton = By.xpath("//span[contains(text(),'CREATE PERSON')]");
    private By binButton = By.xpath("//button[@aria-label=\"delete-button\"]");
    private By confirmDelete = By.xpath("//button[contains(text(),'Delete')]");
    private By newPersonText = By.xpath("//b[contains(text(), 'New Person')]");
    private By editPersonText = By.xpath("//b[contains(text(), 'Edit Person')]");
    private By fullNameField = By.name("people_name");

    public PeoplePage(WebDriver driver)
    {
        super(driver);
    }

    public void clickOnCreatePerson()
    {
        click(createPersonButton);
    }

    public void enterFullName(String fullName)
    {
        type(fullName, fullNameField);
    }

    public void pickFromDropDown(String dropDown, String text)
    {
        String dropDownXpath = firstPartOfDropDownXpath + dropDown + lastPartOfDropDownXpath;
        By pickFromDropDown = By.xpath(dropDownXpath);
        click(pickFromDropDown);

        String selectXpath = "//span[contains(text(),'" + text + "')]";
        By selectFromDropDown = By.xpath(selectXpath);
        click(selectFromDropDown);

        click(newPersonText);
    }

    public List<String> getNameFromPeople(List<WebElement> elements) throws InterruptedException
    {
        List<String> names = new ArrayList<>();

        for (WebElement e : elements){
            names.add(e.getText());
        }
        return names;
    }

    public String getName(String fullName)
    {
        return fullName.split(" ")[0];
    }

    public String getSurname(String fullName)
    {
        return fullName.split(" ")[1];
    }

    public void createNewPerson(String fullName, String technology, String seniority, String team)
    {
        enterFullName(fullName);
        pickFromDropDown("technologies", technology);
        pickFromDropDown("seniority", seniority);
        pickFromDropDown("role", team);
        clickOnSubmit();
    }

    public void updateAllNamesOfPeopleAndSwitchSideOfName() throws InterruptedException {

        waitForVisibiltyLocator(createPersonButton);

        List<WebElement> nameBox = driver.findElements(By.xpath("//*[@class = 'list-group-item list-group-item-action']"));

        List<String> names = getNameFromPeople(nameBox);

        for (String fullName : names)
        {
            By personDetails = By.xpath("//a[contains(text(),'" + fullName + "')]");
            click(personDetails);

            String firstName = getName(fullName);
            String lastName = getSurname(fullName);

            type(lastName + " " + firstName, fullNameField);
            click(editPersonText);
            clickOnSubmit();

            waitForVisibiltyLocator(createPersonButton);
        }
    }

    public void deleteAllPeople() throws InterruptedException {

        waitForVisibiltyLocator(createPersonButton);

        List<WebElement> nameBox = driver.findElements(By.xpath("//*[@class = 'list-group-item list-group-item-action']"));

        List<String> names = getNameFromPeople(nameBox);

        for (String fullName : names)
        {
            By personDetails = By.xpath("//a[contains(text(),'" + fullName + "')]");
            click(personDetails);

            click(binButton);
            click(confirmDelete);

            waitForVisibiltyLocator(createPersonButton);
        }
    }

    public boolean isCreatedPerson(String fullName){

        By personDetails = By.xpath("//a[contains(text(),'" + fullName + "')]");

        waitForVisibiltyLocator(personDetails);

        return isDisplayed(personDetails);
    }

    public String textForEmptyPeoplePage(){

        WebElement textForEmptyPeoplePage = driver.findElement(By.xpath("//span[@class=\"muted-text\"]"));

        waitForVisibitlityWebElement(textForEmptyPeoplePage);

        return textForEmptyPeoplePage.getText();
    }

    public String textForExistingPerson(String fullName){

        String existingPerson = "//div[contains(text(),'" + fullName + " already exists')]";

        WebElement textForExistingPerson = driver.findElement(By.xpath(existingPerson));

        waitForVisibitlityWebElement(textForExistingPerson);

        return textForExistingPerson.getText();

    }
}
