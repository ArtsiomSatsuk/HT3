package constants;

import java.util.regex.Pattern;

public class RegularExpressions {

    //for separating inputted paths
    public static final String INPUT_SEPARATOR_REGEX = "(\\S*)===(\\S*)";

    //for separating commands in InstructionHandler class
    private static final String OPEN_WITH_TIMEOUT_INT = "\\s*(open)\\s+\"(.*?)\"\\s+\"(\\d+)\"";
    private static final String OPEN_WITH_TIMEOUT_FLOAT = "\\s*(open)\\s+\"(.*?)\"\\s+\"(\\d*.\\d+)\"";
    private static final String OPEN_WITHOUT_TIMEOUT = "\\s*(open)\\s+\"(.*?)\"";
    private static final String CHECK_LINK_PRESENT_BY_HREF = "\\s*(checkLinkPresentByHref)\\s+\"(.*?)\"";
    private static final String CHECK_LINK_PRESENT_BY_NAME = "\\s*(checkLinkPresentByName)\\s+\"(.*?)\"";
    private static final String CHECK_PAGE_TITLE = "\\s*(checkPageTitle)\\s+\"(.*?)\"";
    private static final String CHECK_PAGE_CONTAINS = "\\s*(checkPageContains)\\s+\"(.*?)\"";

    //for separating commands in InstructionHandler class
    public static final Pattern openWithIntTimeoutPattern = Pattern.compile(OPEN_WITH_TIMEOUT_INT, Pattern.CASE_INSENSITIVE);
    public static final Pattern openWithFloatTimeoutPattern = Pattern.compile(OPEN_WITH_TIMEOUT_FLOAT, Pattern.CASE_INSENSITIVE);
    public static final Pattern openWithoutTimeoutPattern = Pattern.compile(OPEN_WITHOUT_TIMEOUT, Pattern.CASE_INSENSITIVE);
    public static final Pattern checkLinkPresentByHrefPattern = Pattern.compile(CHECK_LINK_PRESENT_BY_HREF, Pattern.CASE_INSENSITIVE);
    public static final Pattern checkLinkPresentByNamePattern = Pattern.compile(CHECK_LINK_PRESENT_BY_NAME, Pattern.CASE_INSENSITIVE);
    public static final Pattern checkPageTitlePattern = Pattern.compile(CHECK_PAGE_TITLE, Pattern.CASE_INSENSITIVE);
    public static final Pattern checkPageContainsPattern = Pattern.compile(CHECK_PAGE_CONTAINS, Pattern.CASE_INSENSITIVE);

}
