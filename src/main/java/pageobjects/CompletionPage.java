package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletionPage extends BasePage{

    private final By formCompletionMessage = By.xpath("//h1[@data-sentry-element='Title']");

    public CompletionPage(WebDriver driver) {
        super(driver);
    }

    public String getFormCompletionMessage() {
        waitForElementPresence(formCompletionMessage, 10);
        return  driver.findElement(formCompletionMessage).getText().trim();
    }
}
