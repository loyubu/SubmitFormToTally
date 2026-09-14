package utils;

/**
 * Shareable CV links used by the fixed shortlist scenarios. Read from the
 * environment so the repository holds no links to live Drive files.
 */
public final class CvLinks {

    private static final String ENV_KEY = "CV_LINK_VALID";
    private static final String PROP_KEY = "cv.link.valid";

    private CvLinks() {
    }

    public static String valid() {
        String link = System.getenv(ENV_KEY);

        if (link == null || link.trim().isEmpty()) {
            link = System.getProperty(PROP_KEY);
        }

        if (link == null || link.trim().isEmpty()) {
            throw new IllegalStateException(
                    "No CV link configured. Set the " + ENV_KEY + " environment variable, or pass -D"
                            + PROP_KEY + "=<url> on the Maven command line. See .env.example.");
        }

        return link.trim();
    }
}
