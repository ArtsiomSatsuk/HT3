package service;

import java.time.Duration;
import java.time.Instant;

import static service.LogWriter.logBuffer;

public class Timer {

    private static Instant start;
    private static Instant stop;

    public static void start() {
        start = Instant.now();
    }

    public static void stop(Instruction instruction) {
        stop = Instant.now();
        if (TestResult.status ==true){
            logBuffer.append("\n+").append(instruction).append(getDuration());
            TestResult.succeeded++;
        } else {
            logBuffer.append("\n!").append(instruction).append(getDuration());
            TestResult.failed++;
        }
        TestResult.status =true;
    }

    private static String getDuration() {
        Duration duration = Duration.between(start, stop);
//        System.out.println((double)duration.toSeconds()/1000 + " seconds");
        return duration.toString();
    }
}