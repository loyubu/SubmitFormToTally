package testcases;

import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CompletionPage;
import utils.CandidateData;
import utils.CvLibrary;

import java.time.Duration;

import static utils.Constants.FORM_COMPLETION_TEXT;

public class SubmitFormToTallyTests extends BaseTest{

    private SubmitFormToTallyTests() {
    }

    @Test(priority = 1, groups = {"Retest", "No-CV"})
    public void sendFormWithNoCv() {
        CandidateData data = new CandidateData();

        formPage.enterFullName(data.getFullName())
                .enterEmail(data.getEmail())
                .enterPhoneNumber(data.getPhone())
                .selectPosition(data.getPosition())
                .selectExperienceLevel(data.getExperience())
                .enterCurrentLocation(data.getCurrentLocation())
                .clickSubmit();

        CompletionPage completionPage = new CompletionPage(driver);

        String confirmationText = completionPage.getFormCompletionMessage();

        Assert.assertEquals(confirmationText, FORM_COMPLETION_TEXT,
                "Tally did not confirm the no-CV submission for " + data.getEmail());

        System.out.println("Submitted By: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + data.getPosition() +
                " | Years of Exp: " + data.getExperience());
    }

    @Test(priority = 2, groups = {"Retest", "Selection"})
    public void sendFormThatMatchSelectionCriteria() {
        CandidateData data = new CandidateData();
        String position = "Warehouse Supervisor";
        String experienceLevel = "8+ years";
        String cvLink = CvLibrary.forRole(position, experienceLevel);

        formPage.enterFullName(data.getFullName())
                .enterEmail(data.getEmail())
                .enterPhoneNumber(data.getPhone())
                .selectPosition(position)
                .selectExperienceLevel(experienceLevel)
                .enterCvLink(cvLink)
                .enterCurrentLocation(data.getCurrentLocation())
                .clickSubmit();

        CompletionPage completionPage = new CompletionPage(driver);

        String confirmationText = completionPage.getFormCompletionMessage();

        Assert.assertEquals(confirmationText, FORM_COMPLETION_TEXT);

        System.out.println("Submitted By: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + position +
                " | Years of Exp: " + experienceLevel);
    }

    @Test(priority = 3, groups = {"Retest", "No-selection"})
    public void sendFormThatDoesNotMatchSelectionCriteria() {
        CandidateData data = new CandidateData();
        String position = "Logistics Coordinator";
        String experienceLevel = "0 - 2 years";
        String cvLink = CvLibrary.forRole(position, experienceLevel);

        formPage.enterFullName(data.getFullName())
                .enterEmail(data.getEmail())
                .enterPhoneNumber(data.getPhone())
                .selectPosition(position)
                .selectExperienceLevel(experienceLevel)
                .enterCvLink(cvLink)
                .enterCurrentLocation(data.getCurrentLocation())
                .clickSubmit();

        CompletionPage completionPage = new CompletionPage(driver);

        String confirmationText = completionPage.getFormCompletionMessage();

        Assert.assertEquals(confirmationText, FORM_COMPLETION_TEXT);

        System.out.println("Submitted By: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + position +
                " | Years of Exp: " + experienceLevel);
    }

    @Test(invocationCount = 7, priority = 4, groups = {"Retest", "Multiple-7-tests"})
    public void sendFormWithRandomData() {
        CandidateData data = new CandidateData();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));

        formPage.enterFullName(data.getFullName())
                .enterEmail(data.getEmail())
                .enterPhoneNumber(data.getPhone())
                .selectPosition(data.getPosition())
                .selectExperienceLevel(data.getExperience())
                .enterCvLink(data.getCvLink())
                .enterCurrentLocation(data.getCurrentLocation())
                .clickSubmit();

        CompletionPage completionPage = new CompletionPage(driver);

        String confirmationText = completionPage.getFormCompletionMessage();

        Assert.assertEquals(confirmationText, FORM_COMPLETION_TEXT);

        System.out.println("Submitted: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + data.getPosition() +
                " | Years of Exp: " + data.getExperience());

        // 5-second pause between executions
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }

}
