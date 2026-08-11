package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

   protected WebDriver driver;
   protected WebDriverWait wait;
   protected JavascriptExecutor js;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitForElementPresence(By by, int secs) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void jsSelector(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }
}
