package utils;

import com.github.javafaker.Faker;

import java.util.*;

import static utils.CandidateData.NigerianLocationGenerator.generateNigerianLocation;
import static utils.Constants.*;

public class CandidateData {

    private final String fullName;
    private final String email;
    private final String phone;
    private final String position;
    private final String experience;
    private final String currentLocation;
    private final String cvLink;

    public CandidateData() {
        Faker faker = new Faker(new Locale("en", "NG"));
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
        this.currentLocation = generateNigerianLocation();

        boolean includeCV = random.nextDouble() > 0.1;
        this.cvLink = includeCV ? CvLibrary.forRole(this.position, this.experience) : "";
    }



    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPosition() { return position; }
    public String getExperience() { return experience; }
    public String getCurrentLocation() { return currentLocation; }
    public String getCvLink() { return cvLink; }



    public static class NigerianLocationGenerator {

        private static final Map<String, List<String>> NIGERIAN_LOCATIONS = new HashMap<>();

        static {
            NIGERIAN_LOCATIONS.put("Abia State",
                    Arrays.asList("Aba", "Umuahia", "Ohafia", "Arochukwu"));

            NIGERIAN_LOCATIONS.put("Adamawa State",
                    Arrays.asList("Yola", "Mubi", "Jimeta", "Numan"));

            NIGERIAN_LOCATIONS.put("Akwa Ibom State",
                    Arrays.asList("Uyo", "Eket", "Ikot Ekpene", "Oron"));

            NIGERIAN_LOCATIONS.put("Anambra State",
                    Arrays.asList("Awka", "Onitsha", "Nnewi", "Ekwulobia"));

            NIGERIAN_LOCATIONS.put("Bauchi State",
                    Arrays.asList("Bauchi", "Azare", "Misau", "Jama'are"));

            NIGERIAN_LOCATIONS.put("Bayelsa State",
                    Arrays.asList("Yenagoa", "Brass", "Ogbia", "Sagbama"));

            NIGERIAN_LOCATIONS.put("Benue State",
                    Arrays.asList("Makurdi", "Gboko", "Otukpo", "Katsina-Ala"));

            NIGERIAN_LOCATIONS.put("Borno State",
                    Arrays.asList("Maiduguri", "Biu", "Monguno", "Dikwa"));

            NIGERIAN_LOCATIONS.put("Cross River State",
                    Arrays.asList("Calabar", "Ikom", "Ogoja", "Obudu"));

            NIGERIAN_LOCATIONS.put("Delta State",
                    Arrays.asList("Asaba", "Warri", "Sapele", "Ughelli", "Agbor", "Effurun"));

            NIGERIAN_LOCATIONS.put("Ebonyi State",
                    Arrays.asList("Abakaliki", "Afikpo", "Onueke", "Ishieke"));

            NIGERIAN_LOCATIONS.put("Edo State",
                    Arrays.asList("Benin City", "Auchi", "Ekpoma", "Uromi", "Igarra"));

            NIGERIAN_LOCATIONS.put("Ekiti State",
                    Arrays.asList("Ado-Ekiti", "Ikere", "Ilawe", "Ijero"));

            NIGERIAN_LOCATIONS.put("Enugu State",
                    Arrays.asList("Enugu", "Nsukka", "Oji River", "Agbani"));

            NIGERIAN_LOCATIONS.put("Gombe State",
                    Arrays.asList("Gombe", "Kumo", "Billiri", "Kaltungo"));

            NIGERIAN_LOCATIONS.put("Imo State",
                    Arrays.asList("Owerri", "Orlu", "Okigwe", "Oguta"));

            NIGERIAN_LOCATIONS.put("Jigawa State",
                    Arrays.asList("Dutse", "Hadejia", "Gumel", "Birnin Kudu"));

            NIGERIAN_LOCATIONS.put("Kaduna State",
                    Arrays.asList("Kaduna", "Zaria", "Kafanchan", "Kajuru"));

            NIGERIAN_LOCATIONS.put("Kano State",
                    Arrays.asList("Kano", "Wudil", "Gwarzo", "Rano"));

            NIGERIAN_LOCATIONS.put("Katsina State",
                    Arrays.asList("Katsina", "Funtua", "Daura", "Malumfashi"));

            NIGERIAN_LOCATIONS.put("Kebbi State",
                    Arrays.asList("Birnin Kebbi", "Argungu", "Yauri", "Zuru"));

            NIGERIAN_LOCATIONS.put("Kogi State",
                    Arrays.asList("Lokoja", "Okene", "Idah", "Anyigba"));

            NIGERIAN_LOCATIONS.put("Kwara State",
                    Arrays.asList("Ilorin", "Offa", "Jebba", "Lafiagi"));

            NIGERIAN_LOCATIONS.put("Lagos State",
                    Arrays.asList("Lagos", "Ikeja", "Lekki", "Ikorodu", "Badagry", "Epe"));

            NIGERIAN_LOCATIONS.put("Nasarawa State",
                    Arrays.asList("Lafia", "Keffi", "Karu", "Akwanga"));

            NIGERIAN_LOCATIONS.put("Niger State",
                    Arrays.asList("Minna", "Suleja", "Bida", "Kontagora"));

            NIGERIAN_LOCATIONS.put("Ogun State",
                    Arrays.asList("Abeokuta", "Ijebu-Ode", "Sagamu", "Ota", "Ilaro"));

            NIGERIAN_LOCATIONS.put("Ondo State",
                    Arrays.asList("Akure", "Ondo", "Owo", "Ikare"));

            NIGERIAN_LOCATIONS.put("Osun State",
                    Arrays.asList("Osogbo", "Ile-Ife", "Ilesa", "Ede", "Ikire"));

            NIGERIAN_LOCATIONS.put("Oyo State",
                    Arrays.asList("Ibadan", "Ogbomosho", "Oyo", "Iseyin", "Eruwa"));

            NIGERIAN_LOCATIONS.put("Plateau State",
                    Arrays.asList("Jos", "Bukuru", "Barkin Ladi", "Pankshin"));

            NIGERIAN_LOCATIONS.put("Rivers State",
                    Arrays.asList("Port Harcourt", "Bonny", "Eleme", "Ahoada", "Omoku"));

            NIGERIAN_LOCATIONS.put("Sokoto State",
                    Arrays.asList("Sokoto", "Tambuwal", "Gwadabawa", "Wurno"));

            NIGERIAN_LOCATIONS.put("Taraba State",
                    Arrays.asList("Jalingo", "Wukari", "Bali", "Gembu"));

            NIGERIAN_LOCATIONS.put("Yobe State",
                    Arrays.asList("Damaturu", "Potiskum", "Gashua", "Nguru"));

            NIGERIAN_LOCATIONS.put("Zamfara State",
                    Arrays.asList("Gusau", "Kaura Namoda", "Talata Mafara", "Tsafe"));

            NIGERIAN_LOCATIONS.put("Federal Capital Territory",
                    Arrays.asList("Abuja", "Gwagwalada", "Kubwa", "Bwari", "Kuje", "Nyanya"));
        }

        public static String generateNigerianLocation() {

            Random random = new Random();

            // Select a random state
            List<String> states = new ArrayList<>(NIGERIAN_LOCATIONS.keySet());
            String state = states.get(random.nextInt(states.size()));

            // Select a town belonging to that state
            List<String> towns = NIGERIAN_LOCATIONS.get(state);
            String town = towns.get(random.nextInt(towns.size()));

            return town + ", " + state + ", Nigeria";
        }

    }


}