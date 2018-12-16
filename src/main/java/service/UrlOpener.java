package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static constants.Constants.*;
import static service.LogWriter.logBuffer;

public class UrlOpener {

    protected Document openUrl(Instruction instr) throws IOException, UnknownHostException {
        if (instr.getThirdCommand() == null) {
            logBuffer.append(LINE_SEP).append(NO_SPECIFIED_TIMEOUT_MSG);
            return openUrlWithoutSpecifiedTimeout(instr);
        }
        return openUrlWithSpecifiedTimeout(instr);
    }

    private Document openUrlWithoutSpecifiedTimeout(Instruction instr) throws IOException, UnknownHostException {
        try {
            return Jsoup.connect(instr.getSecondCommand()).get();
        } catch (IllegalArgumentException e) {
            return Jsoup.connect("http://" + instr.getSecondCommand()).get();
        } catch (SocketTimeoutException e) {
            ResultsRecorder.status = false;
            return Jsoup.connect(instr.getSecondCommand()).get();
        }
    }

    private Document openUrlWithSpecifiedTimeout(Instruction instr) throws IOException, UnknownHostException {
        try {
            if (instr.getTimeout() <= 0) {
                logBuffer.append(LINE_SEP).append(WRONG_TIMEOUT_MSG);
                return openUrlWithoutSpecifiedTimeout(instr);
            } else {
                return Jsoup.connect(instr.getSecondCommand()).timeout(instr.getTimeout()).get();
            }
        } catch (IllegalArgumentException e) {
            return openUrlWithAddingProtocol(instr);
        } catch (SocketTimeoutException e) {
            ResultsRecorder.status = false;
            return Jsoup.connect(instr.getSecondCommand()).get();
        }
    }

    private Document openUrlWithAddingProtocol(Instruction instr) throws IOException, UnknownHostException {
        try {
            return Jsoup.connect("http://" + instr.getSecondCommand()).timeout(instr.getTimeout()).get();
        } catch (SocketTimeoutException e) {
            ResultsRecorder.status = false;
            return Jsoup.connect("http://" + instr.getSecondCommand()).get();
        }
    }
}


