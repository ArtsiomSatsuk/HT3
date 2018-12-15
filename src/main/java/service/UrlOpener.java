package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static constants.Constants.EXPIRED_TIMEOUT_MSG;
import static constants.Constants.NO_SPECIFIED_TIMEOUT_MSG;

public class UrlOpener {

    protected Document openUrl(Instruction instr) throws IOException, UnknownHostException {
        if (instr.getThirdCommand() == null) {
            System.out.println(NO_SPECIFIED_TIMEOUT_MSG);
            return openUrlWithoutSpecifiedTimeout(instr);
        } else return openUrlWithSpecifiedTimeout(instr);
    }

    private Document openUrlWithoutSpecifiedTimeout(Instruction instr) throws IOException, UnknownHostException {
        try {
            return Jsoup.connect(instr.getSecondCommand()).get();
        } catch (IllegalArgumentException e) {
            return Jsoup.connect("http://" + instr.getSecondCommand()).get();
        } catch (SocketTimeoutException e) {
            TestResult.status = false;
            System.err.println(EXPIRED_TIMEOUT_MSG);
            return Jsoup.connect(instr.getSecondCommand()).get();
        }
    }

    private Document openUrlWithSpecifiedTimeout(Instruction instr) throws IOException, UnknownHostException  {
        try {
            return Jsoup.connect(instr.getSecondCommand()).timeout(instr.getTimeout()).get();
        }catch (IllegalArgumentException e) {
            return openUrlWithAddingProtocol(instr);
        }
        catch (SocketTimeoutException e) {
            TestResult.status = false;
            System.out.println(EXPIRED_TIMEOUT_MSG);
            return Jsoup.connect(instr.getSecondCommand()).get();
        }
    }

    private Document openUrlWithAddingProtocol(Instruction instr) throws IOException, UnknownHostException {
        try {
            return Jsoup.connect("http://"+instr.getSecondCommand()).timeout(instr.getTimeout()).get();
        } catch (SocketTimeoutException e) {
            TestResult.status = false;
            System.out.println(EXPIRED_TIMEOUT_MSG);
            return Jsoup.connect("http://"+instr.getSecondCommand()).get();
        }
    }
}


