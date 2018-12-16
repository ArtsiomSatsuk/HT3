package constants;

import java.io.File;

public class Constants {

    public static final String INSTRUCTIONS_EXAMPLE = " You had to open website firstly, put command 'open' in the first" +
            " string of your instruction file, use the following example:\nopen \"http://www.google.com\" \"3\"" +
            "\ncheckPageTitle \"Google Search Page\" \ncheckPageContains \"The best search engine\"";

    public static final String INPUT_EXAMPLE = "\nPathToFirstInstructions===PathToFirstLogFile" +
            " PathToSecondInstructions===PathToSecondLogFile \nor just specify paths to files with instructions like this: " +
            "PathToFirstInstructionFile PathToSecondInstructionFile";

    public static final String LINE_SEP = System.getProperty("line.separator");

    public static final String REQUIRED_EXTENSION = ".txt";

    //Exception messages
    public static final String MALFORMED_INPUT_EXC_MSG = "This file with instructions will not be processed. Turn your file" +
            " with instructions in this charset - 'windows-1251', file - %s%n";
    public static final String UNKNOWN_HOST_EXC_MSG = "Couldn't open website with this instruction (Unknown host) - %s," +
            " file with instructions will not be processed - %s%n";
    public static final String INVALID_PATH_EXC_MSG = "Invalid path was specified. Input parameters correctly, follow this example: ";
    public static final String NO_ARGUMENTS_MSG = "You haven't entered any arguments, use this example:";
    public static final String WRONG_TIMEOUT_MSG = "[Timeout cannot be less or equal to zero, default timeout value will be used (30sec)]";
    public static final String NO_SPECIFIED_TIMEOUT_MSG = "[Timeout wasn't specified, default timeout value will be used (30sec)]";
    public static final String NO_PATH_TO_LOG_MSG = "Path to log file wasn't specified for these instructions - %s%n";
    public static final String NO_SUCH_FILE_EXC_MSG = "You had to enter path to file with instructions as first parameter." +
            " Such file doesn't exist - %s%n";
    public static final String FILE_WILL_NOT_BE_PROCESSED_MSG = "This file with instructions will not be processed - %s%n";
    public static final String INCORRECT_INSTRUCTION_MSG = "There is no such command. - %s\nUse the following example:" +
            " \nopen \"http:www.google.com\" \"3\"\ncheckPageTitle \"Google Search Page\" \ncheckPageContains \"The best search engine\"";

    //InputHandler messages
    public static final String FILE_IS_NOT_READABLE_MSG = "Can't read this file - %s%n";
    public static final String WRONG_EXTENSION_MSG = "You had to specify path to file with instructions with 'txt' extension, your file - %s%n";
    public static final String INSTR_FILE_IS_DIRECTORY_MSG = "You had to specify path to file with instructions as first" +
            " parameter. You entered path to directory - %s%n";

    //Output log messages
    public static final String DEFAULT_LOG_PATH = System.getProperty("user.home") + File.separator + "output_log_HT3.txt";
    public static final String NEW_LOG_FILE_MSG = "Log file for this file with instructions - %s was successfully created here - %s%n";
    public static final String CHARSET_LOG_MSG = "Encoding of this file - 'windows-1251'";
    public static final String WRONG_LOG_EXTENSION_MSG = "You had to specify path to log file with 'txt' extension - ";
    public static final String FILE_IS_NOT_WRITABLE = "Can't write in this file - ";
    public static final String LOG_IS_DIRECTORY = "Specified path to log file is a directory - ";
    public static final String CHANGING_PATH_MSG = "Path to log file will be changed";

    public static final String CHARSET_NAME = "windows-1251";
}
