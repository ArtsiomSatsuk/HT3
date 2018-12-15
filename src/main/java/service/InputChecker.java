package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static constants.Constants.*;

public class InputChecker {


    public void verifyCmdInput(String[] args) throws IOException {
        StringBuilder inputAssert = new StringBuilder();
        Path pathToInstructions = Paths.get(args[0]);
        if (Files.isDirectory(pathToInstructions)) {
            inputAssert.append(String.format(INSTR_FILE_IS_DIRECTORY_MSG, pathToInstructions));
        }
//        if (Files.size(pathToInstructions)==0){
//            inputAssert.append(String.format(EMPTY_FILE_MSG, pathToInstructions));
//        }
        if (!(args[0].regionMatches(true, args[0].length() - 4, REQUIRED_EXTENSION, 0, 4))) {
            inputAssert.append(WRONG_EXTENSION_MSG);
        }
        if (!Files.isReadable(pathToInstructions)) {
            inputAssert.append(String.format(FILE_IS_NOT_READABLE, pathToInstructions));
        }
        if (!inputAssert.toString().equals("")) {
            System.out.println(inputAssert.toString());
//            return false;
        }
//        return true;
    }
}