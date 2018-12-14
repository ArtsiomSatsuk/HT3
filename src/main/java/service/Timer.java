package service;

import static service.LogWriter.logBuffer;

public class Timer {

    public static double timeBuffer = 0;

    private String lineSep = System.getProperty("line.separator");

    private long startTime = 0;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop(Instruction instruction) {
        double duration = getDuration();
        timeBuffer += duration;
        startTime = 0;
        if (TestResult.status) {
            logBuffer.append(lineSep).append("+").append(instruction).append(duration);
            TestResult.passed++;
        } else {
            logBuffer.append(lineSep).append("!").append(instruction).append(duration);
            TestResult.failed++;
        }
        TestResult.status = true;
    }

    private double getDuration() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }

}