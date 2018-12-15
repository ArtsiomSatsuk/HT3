package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static constants.Constants.EXTENSION;

public class InputChecker {


    public boolean verifyCmdInput(String[] args) {
        StringBuilder inputAssert = new StringBuilder();
        Path path = Paths.get(args[0]);
        if (Files.isDirectory(path)) {
            inputAssert.append(String.format("You had to specify path to file with instructions as first parameter. You entered path to directory - %s%n", path));
        }
        if (!(args[0].regionMatches(true, args[0].length() - 4, EXTENSION, 0, 4))) {
            inputAssert.append("You had to specify path to file with 'txt' extension\n");
        }
        if (!Files.isReadable(path)){
            inputAssert.append(String.format("Can't read this file - %s%n", path));
        }
        if (Files.notExists(path)){
            inputAssert.append(String.format("Such file doesn't exist - %s%n", path));
        }
        if (!inputAssert.toString().equals("")){
            System.out.println(inputAssert.toString());
            return false;
        }
        return true;
    }
}