package constants;

import java.io.File;

public class Constants {

    public static final String example = "\nopen \"http://www.google.com\" \"3\"" +
            "\ncheckPageTitle \"Google Search Page\" \ncheckPageContains \"The best search engine\"";

    public static final String LINE_SEP = System.getProperty("line.separator");

    public static final String REQUIRED_EXTENSION = ".txt";

    //Exception messages
    public static final String MALFORMED_INPUT_EXC_MSG = "Turn your file with instructions in this charset - 'windows-1251'";
    public static final String UNKNOWN_HOST_EXC_MSG = "Couldn't open website with this instruction (Unknown host) - %s," +
            " file with instructions - %s%n";
    public static final String NO_ARGUMENTS_MSG = "You haven't entered any arguments";
    public static final String WRONG_TIMEOUT_MSG = "[Timeout cannot be less or equal to zero, default timeout value will be used (30sec)]";
    public static final String NO_SPECIFIED_TIMEOUT_MSG = "[Timeout wasn't specified, default timeout value will be used (30sec)]";
    public static final String NO_PATH_TO_LOG_MSG = "Path to log file wasn't specified";
    public static final String NO_SUCH_FILE_EXC_MSG = "You had to enter path to file with instructions as first parameter." +
            " Such file doesn't exist - %s%n";

    //InputChecker messages
    public static final String FILE_IS_NOT_READABLE = "Can't read this file - %s%n";
    public static final String WRONG_EXTENSION_MSG = "You had to specify path to file with 'txt' extension\n";
    public static final String INSTR_FILE_IS_DIRECTORY_MSG = "You had to specify path to file with instructions as first" +
            " parameter. You entered path to directory - %s%n";
    public static final String EMPTY_FILE_MSG = "This file is empty - %s%n";

    //Output log messages
    public static final String DEFAULT_LOG_PATH = System.getProperty("user.home")+ File.separator+"output_log_HT3.txt";
    public static final String NEW_LOG_FILE_MSG = "Log file was successfully created here - %s%n";
    public static final String CHARSET_LOG_MSG = "Encoding of this file - 'windows-1251'";

}
