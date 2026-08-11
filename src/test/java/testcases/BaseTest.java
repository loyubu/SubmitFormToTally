package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.FormPage;

public class BaseTest {

    public WebDriver driver;
    public final String url = "https://tally.so/r/PdNx90";
    FormPage formPage;

    protected BaseTest() {
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);

        formPage = new FormPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();

    }
}
