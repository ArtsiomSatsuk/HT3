package service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static constants.Constants.*;
import static service.TestResult.failed;
import static service.TestResult.passed;

public class LogWriter {

    public static StringBuilder logBuffer = new StringBuilder();

    private Charset charset = Charset.forName("windows-1251");

    public void writeResult(String pathToLogFile, String pathToInstructions) throws IOException {
        Path finalPath;
        if (!checkSpecifiedPathToLog(pathToLogFile)) {
            finalPath = Paths.get(DEFAULT_LOG_PATH);
        } else {
            finalPath = Paths.get(pathToLogFile);
            Files.deleteIfExists(finalPath);
            try (BufferedWriter output = new BufferedWriter(Files.newBufferedWriter(finalPath, charset))) {
                output.write(CHARSET_LOG_MSG);
                output.newLine();
                output.write("File with instructions - " + pathToInstructions);
                output.newLine();
                output.write(logBuffer.toString());
                if (Files.isRegularFile(finalPath)) {
                    System.out.printf(NEW_LOG_FILE_MSG, finalPath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkSpecifiedPathToLog(String pathToLogFile) throws IOException {
        StringBuilder logAssert = new StringBuilder();
        Path path = Paths.get(pathToLogFile);
        if (Files.isDirectory(path)) {
            logAssert.append(String.format("%nSpecified path to log file is a directory - %s", path));
        }
        if (!Files.isRegularFile(path)) {
            logAssert.append(String.format("Specified path to log file not a regular file - %s", path));
        }
        if (!Files.isWritable(path)) {
            logAssert.append((String.format("%nCan't write in specified log file - %s", path)));
        }
        if (!(pathToLogFile.regionMatches(true, pathToLogFile.length() - 4, EXTENSION, 0, 4))) {
            logAssert.append(String.format("%nYou supposed to specify path to log file with 'txt' extension - %s", path));
        }
        if (!logAssert.toString().matches("\\s*")) {
            System.out.println(logAssert.toString());
            return false;
        }
        return true;
    }

    public void prepareData() {
        logBuffer.append(LINE_SEP).append(String.format("Total tests: %03d%n", (passed + failed)))
                .append(String.format("Passed/Failed: %03d/%03d%n", passed, failed))
                .append(String.format("Total time: %.3f%n", Timer.timeBuffer))
                .append(String.format("Average time: %.3f%n", Timer.timeBuffer / (passed + failed)));
    }

    public void resetData() {
        Timer.timeBuffer = 0;
        passed = 0;
        failed = 0;
        LogWriter.logBuffer.setLength(0);
    }
}
