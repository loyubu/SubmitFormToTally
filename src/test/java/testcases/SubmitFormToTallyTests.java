package testcases;

import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.annotations.Test;
import utils.CandidateData;

import java.time.Duration;

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
                .enterCoverLetter(data.getCoverLetter())
                .clickSubmit();

        System.out.println("Submitted: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + data.getPosition());
    }

    @Test(priority = 2, groups = {"Retest", "Selection"})
    public void sendFormThatMatchSelectionCriteria() {
        CandidateData data = new CandidateData();
        String position = "Warehouse Supervisor";
        String experienceLevel = "8+ years";

        formPage.enterFullName(data.getFullName())
                .enterEmail(data.getEmail())
                .enterPhoneNumber(data.getPhone())
                .selectPosition(position)
                .selectExperienceLevel(experienceLevel)
                .enterCvLink(data.getCvLink())
                .enterCoverLetter(data.getCoverLetter())
                .clickSubmit();

        System.out.println("Submitted: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + data.getPosition());
    }

    @Test(priority = 3, groups = {"Retest", "No-selection"})
    public void sendFormThatDoesNotMatchSelectionCriteria() {
        CandidateData data = new CandidateData();
        String position = "Logistics Coordinator";
        String experienceLevel = "0 - 2 years";

        formPage.enterFullName(data.getFullName())
                .enterEmail(data.getEmail())
                .enterPhoneNumber(data.getPhone())
                .selectPosition(position)
                .selectExperienceLevel(experienceLevel)
                .enterCvLink(data.getCvLink())
                .enterCoverLetter(data.getCoverLetter())
                .clickSubmit();

        System.out.println("Submitted: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + data.getPosition());
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
                .enterCoverLetter(data.getCoverLetter())
                .clickSubmit();

        System.out.println("Submitted: " + data.getFullName() +
                " | Email: " + data.getEmail() +
                " | Position: " + data.getPosition());

        // 5-second pause between executions
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }

}
