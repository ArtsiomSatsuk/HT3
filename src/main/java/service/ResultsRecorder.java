package service;

import static constants.Constants.LINE_SEP;
import static service.LogWriter.logBuffer;

public class ResultsRecorder {

    public static boolean status = true;

    public static int passed = 0;
    public static int failed = 0;

    public static double timeBuffer = 0;

    private long startTime = 0;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop(Instruction instruction) {
        double duration = getDuration();
        timeBuffer += duration;
        startTime = 0;
        if (ResultsRecorder.status) {
            logBuffer.append(LINE_SEP).append("+").append(instruction).append(duration);
            ResultsRecorder.passed++;
        } else {
            logBuffer.append(LINE_SEP).append("!").append(instruction).append(duration);
            ResultsRecorder.failed++;
        }
        ResultsRecorder.status = true;
    }

    private double getDuration() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }

}
