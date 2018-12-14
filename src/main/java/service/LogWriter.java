package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static constants.Constants.EXTENSION;
import static constants.Constants.OUTPUT_LOG_MESSAGE;
import static service.TestResult.failed;
import static service.TestResult.passed;

public class LogWriter {

    public static StringBuilder logBuffer = new StringBuilder();

    Charset charset = Charset.forName("windows-1251");

    public void writeResult(String pathToLogFile, String userHome) throws IOException {

        Path path = Paths.get(pathToLogFile);
        if (checkSpecifiedPathToLog(pathToLogFile)) {
            try (BufferedWriter output = new BufferedWriter(Files.newBufferedWriter(path, charset))) {
                output.write(OUTPUT_LOG_MESSAGE);
                output.newLine();
                output.write(logBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path defaultPath = Paths.get(userHome + File.separator + "outputHT3.txt");
        Files.deleteIfExists(defaultPath);
        try (BufferedWriter output = new BufferedWriter(Files.newBufferedWriter(defaultPath, charset))) {
            output.write(OUTPUT_LOG_MESSAGE);
            output.newLine();
            output.write(logBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSpecifiedPathToLog(String pathToLogFile) throws IOException {
        Path path = Paths.get(pathToLogFile);
        if (!Files.isRegularFile(path)) {
            System.out.println("Specified log file is not a regular file");
            return false;
        }
        if (!Files.isWritable(path)) {
            System.out.println("Can't write in specified log file");
            return false;
        }
        if (Files.notExists(path)) {
            System.out.println("Specified log file path does not exist");
            return false;
        }
        if (!(pathToLogFile.regionMatches(true, pathToLogFile.length() - 4, EXTENSION, 0, 4))) {
            System.out.println("You supposed to specify path to log file with 'txt' extension");
            return false;
        }
        return true;
    }

    private String lineSep = System.getProperty("line.separator");

    public void prepareData() {
        logBuffer.append(lineSep).append(String.format("Total tests: %03d%n", (passed + failed)))
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
