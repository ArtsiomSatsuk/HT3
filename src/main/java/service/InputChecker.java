package service;

import java.io.File;

public class InputChecker {

    private static final String EXTENSION = "txt";

    public void verifyCmdInput(String[] args) {
        if (!verifyFirstParameter(args[0])) {
            return;
        }
    }

    private String getFileExtension(String pathToFile) {
        String extensionOfInputtedFile = null;
        int position = pathToFile.lastIndexOf(".");
        if (position > 0) {
            extensionOfInputtedFile = pathToFile.substring(position);
        }
        return extensionOfInputtedFile;
    }

    private boolean verifyFirstParameter(String path) {
        File file = new File(path);
        if (!(file.isFile()) && (file.canRead())) {
            System.out.println("You had to enter path to file with instructions as first parameter. Such file doesn't exist - " + path);
            return false;
        }
        if (getFileExtension(path).equalsIgnoreCase(EXTENSION)) {
            System.out.println("You had to enter path to file with 'txt' extension. Extension of your file - " + getFileExtension(path));
            return false;
        }
        if (file.length()==0) {
            System.out.println("This file is empty - " + path);
            return false;
        }
        return true;
    }
}