package UI.Utility;

import UI.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public static final String email = "dusanmilic92@gmail.com";
    public static final String password = "test2021!!";
    public static final String URL = "https://qa-sandbox.apps.htec.rs";
    protected LoginPage loginPage;
    protected DashBoardPage dashBoardPage;
    protected PeoplePage peoplePage;
    protected TechnologyPage technologyPage;
    protected PlaygroundPage playgroundPage;
    protected SeniorityPage seniorityPage;

    @BeforeTest
    public void SetUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        loginPage = new LoginPage(driver);
        dashBoardPage = new DashBoardPage(driver);
        peoplePage = new PeoplePage(driver);
        technologyPage = new TechnologyPage(driver);
        playgroundPage = new PlaygroundPage(driver);
        seniorityPage = new SeniorityPage(driver);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL);

        loginPage.loginWith(email, password);
        dashBoardPage.clickOnPlayground();
    }

    @AfterTest
    public void TearDown(){
        dashBoardPage.logOut();
        driver.close();
    }
}
