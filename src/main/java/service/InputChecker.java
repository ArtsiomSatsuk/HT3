package service;

import java.io.File;
import java.nio.file.NoSuchFileException;

import static constants.Constants.EXTENSION;

public class InputChecker {

    public void verifyCmdInput(String[] args) {
        File file = new File(args[0]);
//        if (!(file.exists()) || !(file.isFile())) {
//            System.out.printf("You had to enter path to file with instructions as first parameter. Such file doesn't exist - %s%n", args[0]);
//            System.exit(-1);
//        }
        if (!(args[0].regionMatches(true,args[0].length()-4, EXTENSION,0,4))) {
            System.out.println("You had to enter path to file with 'txt' extension");
            return;
        }
        if (file.length() == 0) {
            System.out.printf("This file is empty - %s%n", args[0]);
            return;
        }
        if (!file.canRead()) {
            System.out.printf("Can't read this file - %s%n", args[0]);
            return;
        }
    }
}