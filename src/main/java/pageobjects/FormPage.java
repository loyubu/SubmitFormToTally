package pageobjects;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage extends BasePage {// Locators
    private final By nameField = By.id("43bf7c75-da13-4e16-b204-417073132b43");
    private final By emailField = By.id("bb5c6e03-3bdd-4531-8264-90180aa1ac5e");
    private final By phoneField = By.id("985f164e-e994-43a4-ae74-1bc08fde2164");
    private final By cvLinkField = By.id("6dee95d4-a67c-4735-be2d-77aa5bbed466");
    private final By currentLocation = By.id("85897ef4-6853-499a-ac65-5369e4a104d4");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By thankYouMessage = By.xpath("//h1[@data-sentry-element='Title']");

  public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage enterFullName(String name) {
        waitForElementPresence(nameField,10);
        WebElement element = driver.findElement(nameField);
        element.sendKeys(name);// Dispatch input event so Tally registers the state change
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        return this;
    }

    public FormPage enterEmail(String email) {
        waitForElementPresence(emailField,10);
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public FormPage enterPhoneNumber(String phone) {
        waitForElementPresence(phoneField,10);
        WebElement phoneInput = driver.findElement(phoneField);
        phoneInput.sendKeys(Keys.CONTROL + "a");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        phoneInput.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        phoneInput.sendKeys(phone);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", phoneInput);
        return this;
    }

    public FormPage selectPosition(String position) {
        By positionLocator = By.xpath("(//fieldset)[1]//label[contains(., '" + position + "')]");
        waitForElementPresence(positionLocator, 10);
        WebElement element= driver.findElement(positionLocator);
        jsSelector(element);
        return this;
    }

    public FormPage selectExperienceLevel(String experience) {
        By experienceLocator = By.xpath("(//fieldset)[2]//label[contains(., '" + experience + "')]");
        waitForElementPresence(experienceLocator, 10);
        WebElement element = driver.findElement(experienceLocator);
        jsSelector(element);
        return this;
    }

    public FormPage enterCvLink(String cvLink) {
        waitForElementPresence(cvLinkField, 5);
        if (cvLink != null && !cvLink.isEmpty()) {
            driver.findElement(cvLinkField).sendKeys(cvLink);
        }
        return this;
    }

    public FormPage enterCurrentLocation(String location) {
        waitForElementPresence(currentLocation, 5);
        driver.findElement(currentLocation).sendKeys(location);
        return this;
    }

    /**
     * @return
     */
    public FormPage clickSubmit() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));
        driver.findElement(submitButton).click();// Wait until Tally redirects or displays the submission confirmation element
        WebDriverWait submitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        submitWait.until(ExpectedConditions.or(
               //xpectedConditions.urlContains("submitted"),
                ExpectedConditions.presenceOfElementLocated(thankYouMessage)
        ));

        // Brief pause to allow background network requests to complete
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));
        return this;
    }

}
