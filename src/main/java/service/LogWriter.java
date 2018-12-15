package service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static constants.Constants.*;
import static service.ResultsRecorder.failed;
import static service.ResultsRecorder.passed;

public class LogWriter {

    public static StringBuilder logBuffer = new StringBuilder();

    private Charset charset = Charset.forName("windows-1251");

    public void writeResult(String pathToLogFile, String pathToInstructions) throws IOException {
        Path outputLog = Paths.get(pathToLogFile);
        if (!checkSpecifiedPathToLog(pathToLogFile)) {
            System.out.println("Path to log file will be changed");
            outputLog = Paths.get(DEFAULT_LOG_PATH);
        }
        if (Files.notExists(outputLog) || (!Files.isRegularFile(outputLog))) {
            Files.createFile(outputLog);
        }
        try (BufferedWriter output = new BufferedWriter(Files.newBufferedWriter(outputLog, charset, StandardOpenOption.APPEND))) {
            output.newLine();
            output.write(CHARSET_LOG_MSG);
            output.newLine();
            output.write("File with instructions - " + pathToInstructions);
            output.write(logBuffer.toString());
            if (Files.isRegularFile(outputLog) && Files.exists(outputLog)) {
                System.out.printf(NEW_LOG_FILE_MSG, outputLog);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSpecifiedPathToLog(String pathToLogFile) {
        StringBuilder logAssert = new StringBuilder();
        Path path = Paths.get(pathToLogFile);
        if (Files.isDirectory(path)) {
            logAssert.append("Specified path to log file is a directory - ").append(path).append(LINE_SEP);
        }
        if (!Files.isWritable(path) && (Files.isRegularFile(path))) {
            logAssert.append("Can't write in this file - ").append(path).append(LINE_SEP);
        }
        if (!(pathToLogFile.regionMatches(true, pathToLogFile.length() - 4, REQUIRED_EXTENSION, 0, 4))) {
            logAssert.append("You supposed to specify path to log file with 'txt' extension - ").append(path);
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
                .append(String.format("Total time: %.3f%n", ResultsRecorder.timeBuffer))
                .append(String.format("Average time: %.3f%n", ResultsRecorder.timeBuffer / (passed + failed)));
    }

    public void resetData() {
        ResultsRecorder.timeBuffer = 0;
        passed = 0;
        failed = 0;
        LogWriter.logBuffer.setLength(0);
    }
}