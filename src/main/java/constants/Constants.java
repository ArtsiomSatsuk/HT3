package constants;

import java.io.File;

public class Constants {

    public static final String example = "\nopen \"http://www.google.com\" \"3\"" +
            "\ncheckPageTitle \"Google Search Page\" \ncheckPageContains \"The best search engine\"";

    public static final String LINE_SEP = System.getProperty("line.separator");

    public static final String EXTENSION = ".txt";

    //Exception messages
    public static final String MALFORMED_INPUT_EXC_MSG = "Turn your file with instructions in this charset - 'windows-1251'";
    public static final String UNKNOWN_HOST_EXC_MSG = "Couldn't open website with this instruction (Unknown host) - %s, file with instructions - %s%n";
    public static final String NO_ARGUMENTS_MSG = "You haven't entered any arguments";
    public static final String NO_SPECIFIED_TIMEOUT_MSG = "[You didn't specify timeout for UrlOpener, default timeout value will be used (30sec)]";
    public static final String EXPIRED_TIMEOUT_MSG = "[Specified time limit has expired, will try to open this page with default timeout again (30sec)]";

    //Output log messages
    public static final String DEFAULT_LOG_PATH = System.getProperty("user.home")+ File.separator+"output_log_HT3.txt";
    public static final String NEW_LOG_FILE_MSG = "New log file file was successfully created here - %s%n";
    public static final String CHARSET_LOG_MSG = "Encoding of this file - 'windows-1251'";

}
