package conferencetrackmanagement.utility;

/**
 * @author neesha
 */

public class Constants {
    public static final double MORNING_SESSION_MAX_MINUTES = 180.0;
    public static final double AFTERNOON_SESSION_MAX_MINUTES = 240.0;
    public static final String MIN_SUFFIX = "min";
    public static final String LIGHTNING_SUFFIX = "lightning";
    public static final int LIGHTNING_MINUTES = 5;
    public static final String REGEX_PATTERN = "(.*)(\\s){1}([0-2]?[0-9]?[0-9]{1}min|lightning)\\b";
}
