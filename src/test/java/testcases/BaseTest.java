package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--headless=new"); // Ensures Chrome runs headlessly on CI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.get(url);

        formPage = new FormPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();

    }
}
