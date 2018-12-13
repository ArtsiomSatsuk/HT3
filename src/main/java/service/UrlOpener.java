package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class UrlOpener {

    protected Document openUrl(Instruction instr) throws IOException, UnknownHostException {
        try {
            if (instr.getTimeout() == null) {
            System.out.println("[You didn't specify timeout for UrlOpener, default timeout value will be used (30sec)]");
            return Jsoup.connect(instr.getSecondCommand()).get();
        }
        return Jsoup.connect(instr.getSecondCommand()).timeout((int) (Double.parseDouble(instr.getTimeout()) * 1000)).get();
        } catch (SocketTimeoutException e) {
            TestResult.status = false;
            System.err.println("[Specified time limit has expired, will try to open this page with default timeout again (30sec)]");
            return Jsoup.connect(instr.getSecondCommand()).get();
        } catch (IllegalArgumentException e){
            return Jsoup.connect("http://"+instr.getSecondCommand()).get();
        }
    }
}
