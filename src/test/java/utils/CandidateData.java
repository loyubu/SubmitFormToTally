package utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CandidateData {

    private final String fullName;
    private final String email;
    private final String phone;
    private final String position;
    private final String experience;
    private final String coverLetter;
    private final String cvLink;

    public CandidateData() {
        Faker faker = new Faker();
        Random random = new Random();

        this.fullName = faker.name().fullName();
        String sanitizedName = fullName.toLowerCase().replaceAll("[^a-z0-9]", "");
        this.email = sanitizedName + "@transsahara.com";

        String[] prefixes = {"803", "806", "814", "703", "706", "810", "812", "903", "909", "913"};
        String randomPrefix = prefixes[random.nextInt(prefixes.length)];
        String randomSuffix = faker.number().digits(7);
        this.phone = "+234" + randomPrefix + randomSuffix;

        String[] positions = {"Delivery Driver", "Logistics Coordinator", "Warehouse Supervisor"};
        String[] experienceLevels = {"0 - 2 years", "3 - 5 years", "6 - 8 years", "8+ years"};

        this.position = positions[random.nextInt(positions.length)];
        this.experience = experienceLevels[random.nextInt(experienceLevels.length)];
        this.coverLetter = faker.lorem().paragraph();

        // Build Matrix
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
        this.cvLink = includeCV ? cvLookupMatrix.get(this.position).get(this.experience) : "";
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPosition() { return position; }
    public String getExperience() { return experience; }
    public String getCoverLetter() { return coverLetter; }
    public String getCvLink() { return cvLink; }
}