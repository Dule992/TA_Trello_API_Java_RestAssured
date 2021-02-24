package UI.TestSuite;

import UI.Utility.BaseTest;
import UI.Utility.DataDriven;
import org.testng.annotations.Test;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class CRUD_People extends BaseTest {


    @Test(dataProvider = "data-technology", dataProviderClass = DataDriven.class)
    public void TC01_createNewTechnologyValid(String technology)
    {
        playgroundPage.clickOnTechnologies();
        technologyPage.createTechnology(technology);

        Boolean actual = technologyPage.isTechnologyCreated(technology);

        Assert.assertTrue(actual);
    }


    @Test(dataProvider = "data-seniority", dataProviderClass = DataDriven.class)
    public void TC02_createNewSeniorityValid(String seniority)
    {
        playgroundPage.clickOnSeniorities();
        seniorityPage.createSeniority(seniority);

        Boolean actual = seniorityPage.isSeniorityCreated(seniority);

        Assert.assertTrue(actual);
    }


    @Test(dataProvider = "data-people", dataProviderClass = DataDriven.class)
    public void TC03_createNewPersonValid(String fullName, String technology, String seniority, String team){

        playgroundPage.clickOnPeoplePage();
        peoplePage.clickOnCreatePerson();
        peoplePage.createNewPerson(fullName,technology,seniority,team);

        Boolean actual = peoplePage.isCreatedPerson(fullName);

        Assert.assertTrue(actual);
    }


    @Test(dataProvider = "data-existingPerson", dataProviderClass = DataDriven.class)
    public void TC04_createAnExistingPerson(String fullName, String technology, String seniority, String team){

        playgroundPage.clickOnPeoplePage();
        peoplePage.clickOnCreatePerson();
        peoplePage.createNewPerson(fullName,technology,seniority,team);

        String actual = peoplePage.textForExistingPerson(fullName);

        System.out.println(actual);

        String expected = fullName + " already exists";

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void TC05_editPeopleAndSwitchSideOfNameAndLastName() throws InterruptedException {

        playgroundPage.clickOnPeoplePage();
        peoplePage.updateAllNamesOfPeopleAndSwitchSideOfName();

        List<WebElement> nameBox = driver.findElements(By.xpath("//*[@class = 'list-group-item list-group-item-action']"));

        List<String> actual = peoplePage.getNameFromPeople(nameBox);

        List<String> names = Arrays.asList(new String[]{"Ivanovic Ivan", "Jovanovic Jovan", "Petrovic Petar", "Markovic Marko"});
        List<String> expected = names;

        Assert.assertEquals(expected,actual);
    }

    /*
    @Test
    public void TC06_removeAllCreatedPeople() throws InterruptedException {

        playgroundPage.clickOnPeoplePage();
        peoplePage.deleteAllPeople();

        String actual = peoplePage.textForEmptyPeoplePage();
        String expected = "No people added yet. Click on CREATE PERSON to add some.";

        Assert.assertEquals(expected,actual);
    }*/


}
