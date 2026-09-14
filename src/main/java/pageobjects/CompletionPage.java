package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletionPage extends BasePage{

    private final By formCompletionMessage = By.xpath("//h1[text()='Thanks for completing this form!']");

    public CompletionPage(WebDriver driver) {
        super(driver);
    }

    public String getFormCompletionMessage() {

        return  driver.findElement(formCompletionMessage).getText();
    }
}
