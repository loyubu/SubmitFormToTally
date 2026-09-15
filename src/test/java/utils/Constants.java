package utils;

public class Constants {

    private static final String URL_ENV_KEY = "TALLY_FORM_URL";
    private static final String URL_PROP_KEY = "tally.form.url";

    /** Expected text on the Tally confirmation screen. */
    public static final String FORM_COMPLETION_TEXT = "Thanks for completing this form!";

    /** Links to sample CVs. */
    public static final String DELIVERY_CV_0_TO_2_YEARS = "https://drive.google.com/file/d/1tYs0n-5f97nVh5Vy7M9H56U7gyCu_EkD/view?usp=drive_link";
    public static final String DELIVERY_CV_3_TO_5_YEARS = "https://drive.google.com/file/d/1kxEZjZ21XyK6o0aCG6RXv1PMzP0D_lIO/view?usp=drive_link";
    public static final String DELIVERY_CV_6_TO_8_YEARS = "https://drive.google.com/file/d/1L_aqGmAi7IJu16bYPrYPwHoTqqUYHX4W/view?usp=drive_link";
    public static final String DELIVERY_CV_8_PLUS_YEARS = "https://drive.google.com/file/d/1HKadxlCyicURtT1RLSF-skYBK1KaaYJz/view?usp=drive_link";
    public static final String LOGISTICS_CV_0_TO_2_YEARS = "https://drive.google.com/file/d/1-C-IRKUrqC-YMRmrBwdXo1FLiiHfZPBG/view?usp=drive_link";
    public static final String LOGISTICS_CV_3_TO_5_YEARS = "https://drive.google.com/file/d/13xbaSVkudQUXt95UepxSqJ7huBc5vbzJ/view?usp=drive_link";
    public static final String LOGISTICS_CV_6_TO_8_YEARS = "https://drive.google.com/file/d/1woL4lxBG0Zmg9fdzIjYTxdO4q_-IrwlJ/view?usp=drive_link";
    public static final String LOGISTICS_CV_8_PLUS_YEARS = "https://drive.google.com/file/d/1cmAqO2ScuFqc6KVUrbMH1diu0JoIaOE9/view?usp=drive_link";
    public static final String WAREHOUSE_CV_0_TO_2_YEARS = "https://drive.google.com/file/d/1SWra9xx-xQGWHutXvT35TdaYRR4Gy1Nx/view?usp=drive_link";
    public static final String WAREHOUSE_CV_3_TO_5_YEARS = "https://drive.google.com/file/d/1pfBSpWcYHvW3F9DfQNkxizR9tnmO--Qu/view?usp=drive_link";
    public static final String WAREHOUSE_CV_6_TO_8_YEARS = "https://drive.google.com/file/d/1DHjztioTcBuCkf65atGEpvMcWcFvmBU8/view?usp=drive_link";
    public static final String WAREHOUSE_CV_8_PLUS_YEARS = "https://drive.google.com/file/d/1oOR4KM_sTZC_H9QAI1Jp07QwrqZ-zA_B/view?usp=drive_link";


    private Constants() {
    }

    /**
     * Target form URL, resolved from the environment so the live form ID is not
     * committed to a public repository.
     *
     * Resolution order:
     *   1. TALLY_FORM_URL environment variable  (used by CI)
     *   2. -Dtally.form.url system property     (convenient for local runs)
     *
     * Fails fast with an actionable message if neither is set, rather than silently
     * targeting the wrong form.
     */
    public static String baseUrl() {
        String url = System.getenv(URL_ENV_KEY);

        if (isBlank(url)) {
            url = System.getProperty(URL_PROP_KEY);
        }

        if (isBlank(url)) {
            throw new IllegalStateException(
                    "Target form URL is not configured. Set the " + URL_ENV_KEY
                            + " environment variable, or pass -D" + URL_PROP_KEY
                            + "=<url> on the Maven command line. See .env.example and the README.");
        }

        return url.trim();
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }








}
