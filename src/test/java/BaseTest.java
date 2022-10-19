import framework.Driver;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected LoginPage loginPage;
    private static final String baseURL = "https://accounts.google.com/";

    @BeforeClass(alwaysRun = true)
    public void beforeClass()  {
        WebDriver driver = Driver.getInstance().getDriver();
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage();
    }

    // navigating to baseURL after each test to start the login process from the beginning
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        Driver.getInstance().getDriver().navigate().to(baseURL);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Driver.getInstance().removeDriver();
    }
}
