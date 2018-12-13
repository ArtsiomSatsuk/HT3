package constants;

import java.util.regex.Pattern;

public class RegularExpressions {

    private static final String OPEN_WITH_TIMEOUT_INT = "\\s*(open)\\s+\"(.*?)\"\\s+\"(\\d+)\"";
    private static final String OPEN_WITH_TIMEOUT_FLOAT = "\\s*(open)\\s+\"(.*?)\"\\s+\"(\\d*.\\d+)\"";
    private static final String OPEN_WITHOUT_TIMEOUT = "\\s*(open)\\s+\"(.*?)\"";
    private static final String CHECK_LINK_PRESENT_BY_HREF = "\\s*(checkLinkPresentByHref)\\s+\"(.*?)\"";
    private static final String CHECK_LINK_PRESENT_BY_NAME = "\\s*(checkLinkPresentByName)\\s+\"(.*?)\"";
    private static final String CHECK_PAGE_TITLE = "\\s*(checkPageTitle)\\s+\"(.*?)\"";
    private static final String CHECK_PAGE_CONTAINS = "\\s*(checkPageContains)\\s+\"(.*?)\"";

    public static final Pattern openWithIntTimeoutPattern = Pattern.compile(OPEN_WITH_TIMEOUT_INT, Pattern.MULTILINE);
    public static final Pattern openWithFloatTimeoutPattern = Pattern.compile(OPEN_WITH_TIMEOUT_FLOAT, Pattern.MULTILINE);
    public static final Pattern openWithoutTimeoutPattern = Pattern.compile(OPEN_WITHOUT_TIMEOUT, Pattern.MULTILINE);
    public static final Pattern checkLinkPresentByHrefPattern = Pattern.compile(CHECK_LINK_PRESENT_BY_HREF, Pattern.MULTILINE);
    public static final Pattern checkLinkPresentByNamePattern = Pattern.compile(CHECK_LINK_PRESENT_BY_NAME, Pattern.MULTILINE);
    public static final Pattern checkPageTitlePattern = Pattern.compile(CHECK_PAGE_TITLE, Pattern.MULTILINE);
    public static final Pattern checkPageContainsPattern = Pattern.compile(CHECK_PAGE_CONTAINS, Pattern.MULTILINE);

}
