package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.Constants.*;
import static constants.RegularExpressions.INPUT_SEPARATOR_REGEX;

public class InputHandler {

    public boolean verifyCmdInput(String pathToInstructions) throws IOException, InvalidPathException {
        StringBuilder inputAssert = new StringBuilder();
        Path instrFile = Paths.get(pathToInstructions);
        if (Files.isDirectory(instrFile)) {
            inputAssert.append(String.format(INSTR_FILE_IS_DIRECTORY_MSG, instrFile));
        }
        if (!(pathToInstructions.regionMatches(true, pathToInstructions.length() - 4, REQUIRED_EXTENSION, 0, 4))) {
            inputAssert.append(String.format(WRONG_EXTENSION_MSG, instrFile));
        }
        if (!Files.isReadable(instrFile)) {
            inputAssert.append(String.format(FILE_IS_NOT_READABLE_MSG, instrFile));
        }
        if (!inputAssert.toString().equals("")) {
            inputAssert.append(String.format(FILE_WILL_NOT_BE_PROCESSED_MSG, instrFile))
                    .append("\nMaybe you specified paths incorrectly, use the following example: ").append(INPUT_EXAMPLE);
            System.out.println(inputAssert.toString());
            return false;
        }
        return true;
    }

    public String[] getPaths(String parameters) {
        String[] paths = new String[2];
        final Pattern pattern = Pattern.compile(INPUT_SEPARATOR_REGEX);
        final Matcher matcher = pattern.matcher(parameters);
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                paths[i - 1] = matcher.group(i);
            }
        } else {
            paths[0] = parameters;
        }
        return paths;
    }
}