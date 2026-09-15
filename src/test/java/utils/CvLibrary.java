package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static utils.Constants.*;

/**
 * Maps a position and experience band to the matching sample CV.
 * Shared by CandidateData (random selection) and the fixed-scenario tests,
 * so the two cannot drift apart.
 */
public final class CvLibrary {

    private static final Map<String, Map<String, String>> MATRIX = buildMatrix();

    private CvLibrary() {
    }

    public static String forRole(String position, String experience) {
        Map<String, String> byExperience = MATRIX.get(position);

        if (byExperience == null) {
            throw new IllegalArgumentException("No CVs held for position: " + position);
        }

        String link = byExperience.get(experience);

        if (link == null) {
            throw new IllegalArgumentException(
                    "No CV held for " + position + " at experience level: " + experience);
        }

        return link;
    }

    private static Map<String, Map<String, String>> buildMatrix() {
        Map<String, Map<String, String>> matrix = new HashMap<>();

        Map<String, String> delivery = new HashMap<>();
        delivery.put("0 - 2 years", DELIVERY_CV_0_TO_2_YEARS);
        delivery.put("3 - 5 years", DELIVERY_CV_3_TO_5_YEARS);
        delivery.put("6 - 8 years", DELIVERY_CV_6_TO_8_YEARS);
        delivery.put("8+ years", DELIVERY_CV_8_PLUS_YEARS);
        matrix.put("Delivery Driver", delivery);

        Map<String, String> logistics = new HashMap<>();
        logistics.put("0 - 2 years", LOGISTICS_CV_0_TO_2_YEARS);
        logistics.put("3 - 5 years", LOGISTICS_CV_3_TO_5_YEARS);
        logistics.put("6 - 8 years", LOGISTICS_CV_6_TO_8_YEARS);
        logistics.put("8+ years", LOGISTICS_CV_8_PLUS_YEARS);
        matrix.put("Logistics Coordinator", logistics);

        Map<String, String> warehouse = new HashMap<>();
        warehouse.put("0 - 2 years", WAREHOUSE_CV_0_TO_2_YEARS);
        warehouse.put("3 - 5 years", WAREHOUSE_CV_3_TO_5_YEARS);
        warehouse.put("6 - 8 years", WAREHOUSE_CV_6_TO_8_YEARS);
        warehouse.put("8+ years", WAREHOUSE_CV_8_PLUS_YEARS);
        matrix.put("Warehouse Supervisor", warehouse);

        return Collections.unmodifiableMap(matrix);
    }
}