package testcases;

import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TallyFormSubmitter {

    public static void main(String[] args) {
        boolean runBatch = true; // IF/ELSE condition flag

        if (runBatch) {
            System.out.println("Starting batch execution: 10 form submissions...");

            for (int i = 1; i <= 10; i++) {
                System.out.println("\n--- Running Submission " + i + " of 10 ---");

                // Execute the single form submission
                submitSingleForm();

                // Wait 5 seconds between runs (except after the last run)
                if (i < 10) {
                    System.out.println("Waiting 5 seconds before the next submission...");
                    Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
                }
            }
            System.out.println("\nBatch execution complete!");

        } else {
            System.out.println("Batch execution disabled. Running a single submission...");
            submitSingleForm();
        }
    }

    // Helper method containing Selenium automation logic
    public static void submitSingleForm() {

        //WebDriver driver = new ChromeDriver();
        WebDriver driver = WebDriverManager.chromedriver().create();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Faker faker = new Faker();
        Random random = new Random();

        try {
            //driver.manage().window().maximize();
            driver.get("https://tally.so/r/PdNx90");

            // 1. Generate Fake Candidate Data
            String fullName = faker.name().fullName();
            String sanitizedName = fullName.toLowerCase().replaceAll("[^a-z0-9]", "");
            String email = sanitizedName + "@transsahara.com";

            String[] prefixes = {"803", "806", "814", "703", "706", "810", "812", "903", "909", "913"};
            String randomPrefix = prefixes[random.nextInt(prefixes.length)];
            String randomSuffix = faker.number().digits(7);
            String phone = "+234" + randomPrefix + randomSuffix;

            String[] positions = {"Delivery Driver", "Logistics Coordinator", "Warehouse Supervisor"};
            String[] experienceLevels = {"0 - 2 years", "3 - 5 years", "6 - 8 years", "8+ years"};

            String selectedPosition = positions[random.nextInt(positions.length)];
            String selectedExperience = experienceLevels[random.nextInt(experienceLevels.length)];
            String coverLetter = faker.lorem().paragraph();

            // CV Lookup Matrix
            Map<String, Map<String, String>> cvLookupMatrix = new HashMap<>();

            Map<String, String> deliveryCv = new HashMap<>();
            deliveryCv.put("0 - 2 years", "https://drive.google.com/file/d/1tYs0n-5f97nVh5Vy7M9H56U7gyCu_EkD/view?usp=drive_link");
            deliveryCv.put("3 - 5 years", "https://drive.google.com/file/d/1kxEZjZ21XyK6o0aCG6RXv1PMzP0D_lIO/view?usp=drive_link");
            deliveryCv.put("6 - 8 years", "https://drive.google.com/file/d/1L_aqGmAi7IJu16bYPrYPwHoTqqUYHX4W/view?usp=drive_link");
            deliveryCv.put("8+ years", "https://drive.google.com/file/d/1HKadxlCyicURtT1RLSF-skYBK1KaaYJz/view?usp=drive_link");
            cvLookupMatrix.put("Delivery Driver", deliveryCv);

            Map<String, String> logisticsCv = new HashMap<>();
            logisticsCv.put("0 - 2 years", "https://drive.google.com/file/d/1-C-IRKUrqC-YMRmrBwdXo1FLiiHfZPBG/view?usp=drive_link");
            logisticsCv.put("3 - 5 years", "https://drive.google.com/file/d/13xbaSVkudQUXt95UepxSqJ7huBc5vbzJ/view?usp=drive_link");
            logisticsCv.put("6 - 8 years", "https://drive.google.com/file/d/1woL4lxBG0Zmg9fdzIjYTxdO4q_-IrwlJ/view?usp=drive_link");
            logisticsCv.put("8+ years", "https://drive.google.com/file/d/1cmAqO2ScuFqc6KVUrbMH1diu0JoIaOE9/view?usp=drive_link");
            cvLookupMatrix.put("Logistics Coordinator", logisticsCv);

            Map<String, String> warehouseCv = new HashMap<>();
            warehouseCv.put("0 - 2 years", "https://drive.google.com/file/d/1SWra9xx-xQGWHutXvT35TdaYRR4Gy1Nx/view?usp=drive_link");
            warehouseCv.put("3 - 5 years", "https://drive.google.com/file/d/1pfBSpWcYHvW3F9DfQNkxizR9tnmO--Qu/view?usp=drive_link");
            warehouseCv.put("6 - 8 years", "https://drive.google.com/file/d/1DHjztioTcBuCkf65atGEpvMcWcFvmBU8/view?usp=drive_link");
            warehouseCv.put("8+ years", "https://drive.google.com/file/d/1oOR4KM_sTZC_H9QAI1Jp07QwrqZ-zA_B/view?usp=drive_link");
            cvLookupMatrix.put("Warehouse Supervisor", warehouseCv);

            boolean includeCV = random.nextDouble() > 0.1;
            String cvLink = includeCV ? cvLookupMatrix.get(selectedPosition).get(selectedExperience) : "";

            // 2. Interact with Form Elements
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input, textarea")));

            WebElement nameField = driver.findElement(By.id("43bf7c75-da13-4e16-b204-417073132b43"));
            nameField.sendKeys(fullName);

            WebElement emailField = driver.findElement(By.id("bb5c6e03-3bdd-4531-8264-90180aa1ac5e"));
            emailField.sendKeys(email);

            WebElement phoneField = driver.findElement(By.id("985f164e-e994-43a4-ae74-1bc08fde2164"));
            // Clear pre-filled value completely
            phoneField.sendKeys(Keys.CONTROL + "a");
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
            phoneField.sendKeys(Keys.BACK_SPACE);
            phoneField.sendKeys(Keys.BACK_SPACE);
            phoneField.sendKeys(Keys.BACK_SPACE);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
            phoneField.sendKeys(phone);


            // Initialize JS Executor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Position Selection via JS Click
            WebElement positionOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//fieldset)[1]//label[contains(., '" + selectedPosition + "')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", positionOption);
            js.executeScript("arguments[0].click();", positionOption);

            // Experience Selection via JS Click
            WebElement experienceOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//fieldset)[2]//label[contains(., '" + selectedExperience + "')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", experienceOption);
            js.executeScript("arguments[0].click();", experienceOption);

            if (!cvLink.isEmpty()) {
                WebElement cvField = driver.findElement(By.id("6dee95d4-a67c-4735-be2d-77aa5bbed466"));
                cvField.sendKeys(cvLink);
            }

            WebElement coverLetterField = driver.findElement(By.id("e3b1118f-99c5-4d80-bc03-2958db870810"));
            coverLetterField.sendKeys(coverLetter);

            // 3. Submit Form
            WebElement submitButton = driver.findElement(By.xpath("//span[@class='sc-3a847d-2 GDQxB']"));
            submitButton.click();

            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            System.out.println("Submitted: " + fullName + " | Email: " + email + " | Position: " + selectedPosition);

            //Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));

        } catch (Exception e) {
            System.err.println("Error during submission: " + e.getMessage());
        } finally {
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            driver.quit();
        }
    }
}